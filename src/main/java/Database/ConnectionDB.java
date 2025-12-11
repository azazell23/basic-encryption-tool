package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	private Connection conn;
	public Connection connect() throws SQLException {
		String host = "localhost:3306";
		String dbName = "simple_msg_app";
		String dbUser = "root";
		String dbPassword = "";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		conn = DriverManager.getConnection(
				"jdbc:mysql://" + host + "/" + dbName,
				dbUser,
				dbPassword
		);
		
		return conn;
	}
	
	public Connection close() throws SQLException {
		conn.close();
		return conn;
	}
}
