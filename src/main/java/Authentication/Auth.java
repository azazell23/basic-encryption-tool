package Authentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Application.User;
import Database.ConnectionDB;

public class Auth {
	public static User attempt(String username, String password) throws Exception {
		Connection db = new ConnectionDB().connect();
		String query = 
				"SELECT * FROM users " +
				"WHERE username = ? AND " +
				"password = ?";
		
		PreparedStatement stmt = db.prepareStatement(query);
		stmt.setString(1, username);
		stmt.setString(2, password);
		
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			return new User(
					rs.getInt("user_id"), 
					rs.getString("username"), 
					rs.getString("password")
			);
		} else {
			throw new Exception("User not found");
		}
	}
}
