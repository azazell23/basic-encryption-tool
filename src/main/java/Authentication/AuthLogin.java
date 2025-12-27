package Authentication;

import DAO.UserDAO;
import Model.User;

public class AuthLogin {
	public static User attempt(String username, String password) throws Exception {
		User user = new UserDAO().searchByNamePassword(username, password);
		if (user != null) {
			return user;
		} else {
			throw new Exception("User not found");
		}
	}
}
