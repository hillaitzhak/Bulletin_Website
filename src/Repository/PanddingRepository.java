package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.Database;
import model.User;

public class PanddingRepository {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -2789556140998785078L;
	
	public PanddingRepository() {
		super();
	}

	public String addUser(User newUser,String hashedPassword){
		try{
			Database.getInstance().init();
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql="INSERT INTO tblpandding (id,username,password,firstName,lastName,email,birthOfDate,gender,telephoneNumber) VALUES (?,?,?,?,?,?,?,?,?);";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, newUser.getId());
			stmt.setString(2, newUser.getUsername());
			stmt.setString(3, hashedPassword);
			stmt.setString(4, newUser.getFirstName());
			stmt.setString(5, newUser.getLastName());
			stmt.setString(6,newUser.getEmail());
			stmt.setString(7, newUser.getBday());
			stmt.setString(8,newUser.getGender());
			stmt.setString(9, newUser.getTelephone());
			stmt.executeUpdate();
			return "success";
		}catch (Exception e){
			e.printStackTrace();
			return "error";
	   }
	}
	
	public String deleteUser(User user) {
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "DELETE FROM tblpandding WHERE id=?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, user.getId());
			stmt.executeUpdate();
			return "success";
		}catch (Exception e) {
			return "SQL ERROR";
		}
	}
	
	public List<User> getAllUsers(){
		try{
			Connection c = Database.getInstance().getConnection();
			Statement stmt = null;
			stmt = c.createStatement();
			String sql="SELECT * FROM tblpandding;";
			ResultSet rs = stmt.executeQuery(sql);
			List<User> allUsers = new ArrayList<>();
			while (rs.next()) {
				User userRequested= new User(rs.getString("username"), rs.getString("id"), rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("birthOfDate"), rs.getString("gender"),rs.getString("telephoneNumber"));
				userRequested.setAdmin(rs.getBoolean("isAdmin"));
				allUsers.add(userRequested);
			}
			return allUsers;
		 }catch (Exception e){
				e.printStackTrace();
				return new ArrayList<User>();
		 }
	}
	
	public User getUserById(String id){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "SELECT * FROM tblpandding WHERE id =?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, id);	
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				User userRequested= new User(rs.getString("username"), rs.getString("id"), rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("birthOfDate"), rs.getString("gender"),rs.getString("telephoneNumber"));
				userRequested.setAdmin(rs.getBoolean("isAdmin"));
				return userRequested;
			} else {
				return null;
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
	   }
	}
	
	public String getPass(String id){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "SELECT * FROM tblpandding WHERE id =?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, id);	
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				String pass = rs.getString("password");
				return pass;
			} else {
				return null;
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
	   }
	}
}
