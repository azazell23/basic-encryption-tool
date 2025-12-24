package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Database.ConnectionDB;
import Model.User;

public class UserDAO {
	public void insert(String username, String password) throws Exception{
		try (Connection db = new ConnectionDB().connect()) {
			String query = "INSERT INTO users (username, password) " +
					"VALUES" +
					"(?, ?)";
			PreparedStatement stmt = db.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, password);

			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public User searchByNamePassword(String username, String password) throws Exception {
		try (Connection db = new ConnectionDB().connect()) {	
			String query = 
					"SELECT * FROM users " +
						"WHERE username = ? AND " +
					"password = ?";
		
			PreparedStatement stmt = db.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, password);
		
			ResultSet rs =  stmt.executeQuery();
			
			if (rs.next()) {
				return new User(
						rs.getInt("user_id"),
						rs.getString("username"),
						rs.getString("password")
						);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public User searchByUsername(String username) throws Exception {
		try (Connection db = new ConnectionDB().connect()) {
			String query = 
				"SELECT * FROM users " +
				"WHERE username = ?";
		
			PreparedStatement stmt = db.prepareStatement(query);
			stmt.setString(1, username);
			
			ResultSet rs =  stmt.executeQuery();
			
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("user_id"));
				return user;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}