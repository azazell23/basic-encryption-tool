package Authentication;

import java.sql.Connection;

import DAO.CryptoKeyDAO;
import DAO.UserDAO;
import Database.ConnectionDB;
import Model.User;

public class AuthRegister {
	public static User attempt(String username, String password, String password_conf) throws Exception {
		if (!password.equals(password_conf)) {
			throw new Exception("Password doesn't match.");
		}
		try (Connection db = new ConnectionDB().connect()) {
			User user = new UserDAO().searchByUsername(username);
			
			if (user != null) {
				throw new Exception("Username is taken.");
			} else {
				new UserDAO().insert(username, password);
				
				User currentUser = AuthLogin.attempt(username, password);
				
				new CryptoKeyDAO().generateForUser(currentUser);
				return currentUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
