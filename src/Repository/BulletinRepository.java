package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.Database;
import model.Bulletin;

public class BulletinRepository {
	
	public String addBulletin(Bulletin newBulletin){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "INSERT INTO tblbulletins ( product, open, close, link, contact, cve, status) VALUES (?,?,?,?,?,?,?);";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, newBulletin.getProduct());
			stmt.setString(2, newBulletin.getOpenDate());
			stmt.setString(3, newBulletin.getCloseDate());
			stmt.setString(4, newBulletin.getLink());
			stmt.setString(5, newBulletin.getContact());
			stmt.setString(6, newBulletin.getCVE());
			stmt.setString(7, newBulletin.getStatus());
			stmt.executeUpdate();
			return "success";
		}catch (Exception e) {
			return e.getMessage();
			}
		}
	
	public String DeleteB(int IDBToDelete){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "DELETE FROM tblbulletins WHERE id=?;";
			stmt = c.prepareStatement(sql);
			stmt.setInt(1, IDBToDelete);
			stmt.executeUpdate();
			return "success";
		}catch (Exception e) {
			return "SQL ERROR";
		}
	}
	
	public String editB(Bulletin bulletin) {
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "UPDATE tblbulletins SET product=?, open=?, close=?, link=?, contact=?, cve=?, status=? WHERE id=?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, bulletin.getProduct());
			stmt.setString(2, bulletin.getOpenDate());
			stmt.setString(3, bulletin.getCloseDate());
			stmt.setString(4, bulletin.getLink());
			stmt.setString(5, bulletin.getContact());
			stmt.setString(6, bulletin.getCVE());
			stmt.setString(7, bulletin.getStatus());
			stmt.setInt(8, bulletin.getId());
			stmt.executeUpdate();
			return "success";
		}catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public Bulletin getBulletin(int id){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "SELECT * FROM tblbulletins WHERE id = ?;";
			stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Bulletin bulletinRequested = new Bulletin(rs.getString("product"), rs.getString("open"), rs.getString("close"), rs.getString("link"), rs.getString("cve"), rs.getString("contact"), rs.getString("status"));
				bulletinRequested.setId(id);
				return bulletinRequested;
			  } 
		}catch (Exception e){
			e.printStackTrace();
			return null;
	   }
		return null;
	}
	
	public List<Bulletin> getAllBulletins(){
		try{
			Connection c = Database.getInstance().getConnection();
			Statement stmt = null;
			stmt = c.createStatement();
			String sql="SELECT * FROM tblbulletins;";
			ResultSet rs = stmt.executeQuery(sql);
			List<Bulletin> allBulletins = new ArrayList<>();
			while (rs.next()) {
				Bulletin bulletinRequested = new Bulletin( rs.getString("product"), rs.getString("open"), rs.getString("close"), rs.getString("link"), rs.getString("cve"), rs.getString("contact"), rs.getString("status"));
				bulletinRequested.setId(rs.getInt("id"));
				allBulletins.add(bulletinRequested);
			  } 
			return allBulletins;
		   }catch (Exception e){
				e.printStackTrace();
				return new ArrayList<Bulletin>();
		   }/*finally{
			   Database.getInstance().closeConnection();
		   }*/
	}
	
	public List<Bulletin> getBulletinsByProduct(String Product){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql="SELECT * FROM tblbulletins WHERE product = ?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, Product);
			ResultSet rs = stmt.executeQuery(sql);
			List<Bulletin> allBulletins = new ArrayList<>();
			while (rs.next()) {
				Bulletin bulletinRequested = new Bulletin( rs.getString("product"), rs.getString("open"), rs.getString("close"), rs.getString("link"), rs.getString("cve"), rs.getString("contact"), rs.getString("status"));
				bulletinRequested.setId(rs.getInt("id"));
				allBulletins.add(bulletinRequested);
			  } 
			return allBulletins;
		   }catch (Exception e){
				e.printStackTrace();
				return new ArrayList<Bulletin>();
		   }
	}
	
	public List<Bulletin> getBulletinsByCVE(String CVE){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql="SELECT * FROM tblbulletins WHERE cve = ?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, CVE);
			ResultSet rs = stmt.executeQuery(sql);
			List<Bulletin> allBulletins = new ArrayList<>();
			while (rs.next()) {
				Bulletin bulletinRequested = new Bulletin( rs.getString("product"), rs.getString("open"), rs.getString("close"), rs.getString("link"), rs.getString("cve"), rs.getString("contact"), rs.getString("status"));
				bulletinRequested.setId(rs.getInt("id"));
				allBulletins.add(bulletinRequested);
			  } 
			return allBulletins;
		   }catch (Exception e){
				e.printStackTrace();
				return new ArrayList<Bulletin>();
		   }
	}
	
	public List<Bulletin> getBulletinsByContact(String contact){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql="SELECT * FROM tblbulletins WHERE contact = ?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, contact);
			ResultSet rs = stmt.executeQuery(sql);
			List<Bulletin> allBulletins = new ArrayList<>();
			while (rs.next()) {
				Bulletin bulletinRequested = new Bulletin( rs.getString("product"), rs.getString("open"), rs.getString("close"), rs.getString("link"), rs.getString("cve"), rs.getString("contact"), rs.getString("status"));
				bulletinRequested.setId(rs.getInt("id"));
				allBulletins.add(bulletinRequested);
			  } 
			return allBulletins;
		   }catch (Exception e){
				e.printStackTrace();
				return new ArrayList<Bulletin>();
		   }finally{
			   Database.getInstance().closeConnection();
		   }
	}
	
	public List<Bulletin> getBulletinsByStatus(String status){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql="SELECT * FROM tblbulletins WHERE status = ?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, status);
			ResultSet rs = stmt.executeQuery(sql);
			List<Bulletin> allBulletins = new ArrayList<>();
			while (rs.next()) {
				Bulletin bulletinRequested = new Bulletin( rs.getString("product"), rs.getString("open"), rs.getString("close"), rs.getString("link"), rs.getString("cve"), rs.getString("contact"), rs.getString("status"));
				bulletinRequested.setId(rs.getInt("id"));
				allBulletins.add(bulletinRequested);
			  } 
			return allBulletins;
		   }catch (Exception e){
				e.printStackTrace();
				return new ArrayList<Bulletin>();
		   }
	}
	
	public List<Bulletin> getBulletinsByAll(String status, String product, String cve, String contact){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql="SELECT * FROM tblbulletins WHERE status = ? and product = ? and contact = ? and cve=?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, status);
			stmt.setString(2, product);
			stmt.setString(3, contact);
			stmt.setString(4, cve);
			ResultSet rs = stmt.executeQuery(sql);
			List<Bulletin> allBulletins = new ArrayList<>();
			while (rs.next()) {
				Bulletin bulletinRequested = new Bulletin( rs.getString("product"), rs.getString("open"), rs.getString("close"), rs.getString("link"), rs.getString("cve"), rs.getString("contact"), rs.getString("status"));
				bulletinRequested.setId(rs.getInt("id"));
				allBulletins.add(bulletinRequested);
			  } 
			return allBulletins;
		   }catch (Exception e){
				e.printStackTrace();
				return new ArrayList<Bulletin>();
		   }
	}
	
	public List<Bulletin> getBulletinsByStatusandProduct(String status, String product){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql="SELECT * FROM tblbulletins WHERE status = ? and product = ?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, status);
			stmt.setString(2, product);
			ResultSet rs = stmt.executeQuery(sql);
			List<Bulletin> allBulletins = new ArrayList<>();
			while (rs.next()) {
				Bulletin bulletinRequested = new Bulletin( rs.getString("product"), rs.getString("open"), rs.getString("close"), rs.getString("link"), rs.getString("cve"), rs.getString("contact"), rs.getString("status"));
				bulletinRequested.setId(rs.getInt("id"));
				allBulletins.add(bulletinRequested);
			  } 
			return allBulletins;
		   }catch (Exception e){
				e.printStackTrace();
				return new ArrayList<Bulletin>();
		   }
	}
	
	public List<String> getProductNames(){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql="SELECT DISTINCT product FROM tblbulletins;";
			stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			List<String> productList = new ArrayList<>();
			while (rs.next()) {
				String p = rs.getString("product");
				productList.add(p);
			  } 
			return productList;
		   }catch (Exception e){
				e.printStackTrace();
				return new ArrayList<String>();
		   }
	}
	
}
		


