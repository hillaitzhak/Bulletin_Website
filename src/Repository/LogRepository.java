package Repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.Database;
import model.Logs;

public class LogRepository {
	
	public String addLog(Logs newLog){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "INSERT INTO tbllogs ( by, date, change) VALUES (?,?,?);";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, newLog.getChangedBy());
			stmt.setString(2, newLog.getDate());
			stmt.setString(3, newLog.getChange());
			stmt.executeUpdate();
			return "success";
		}catch (Exception e) {
			return e.getMessage();
			}
		}
	
	public String DeleteLog(int IDLogToDelete){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "DELETE FROM tbllogs WHERE id=?;";
			stmt = c.prepareStatement(sql);
			stmt.setInt(1, IDLogToDelete);
			stmt.executeUpdate();
			return "success";
		}catch (Exception e) {
			return "SQL ERROR";
		}
	}
	
	public List<Logs> getAllLogs(){
		try{
			Connection c = Database.getInstance().getConnection();
			Statement stmt = null;
			stmt = c.createStatement();
			String sql="SELECT * FROM tbllogs;";
			ResultSet rs = stmt.executeQuery(sql);
			List<Logs> allLogs = new ArrayList<>();
			while (rs.next()) {
				Logs LogsRequested = new Logs( rs.getString("by"), rs.getString("date"), rs.getString("change"));
				LogsRequested.setId(rs.getInt("id"));
				allLogs.add(LogsRequested);
			  } 
			return allLogs;
		   }catch (Exception e){
				e.printStackTrace();
				return new ArrayList<Logs>();
		   }
	}
	
}
