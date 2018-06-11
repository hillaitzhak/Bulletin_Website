package Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.Database;
import model.Password;

public class PasswordRepository {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -2789556140998785078L;
	
	public PasswordRepository() {
		super();
	}
	
	public String addPassword(Password newPassword){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql="INSERT INTO tblp (id,day,month,year) VALUES (?,?,?,?);";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, newPassword.getId());
			stmt.setInt(2, newPassword.getDay());
			stmt.setInt(3, newPassword.getMonth());
			stmt.setInt(4, newPassword.getYear());
			stmt.executeUpdate();
			return "success";
		}catch (Exception e){
			e.printStackTrace();
			return "SQL ERROR";
	   }
	}
	
	public String editPassword(Password pass) {
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "UPDATE tblup SET day=?, month=?, year=? WHERE id=?;";
			stmt = c.prepareStatement(sql);
			stmt.setInt(1, pass.getDay());
			stmt.setInt(2, pass.getMonth());
			stmt.setInt(3, pass.getYear());
			stmt.setString(4, pass.getId());
			stmt.executeUpdate();
			return "success";
		}catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public Password getPasswordById(String id){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "SELECT * FROM tblp WHERE id =?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, id);	
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Password PasswordRequested= new Password(rs.getString("id"), rs.getInt("year"), rs.getInt("day"), rs.getInt("month"));
				return PasswordRequested;
			} else {
				return null;
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
	   }
	}

}
