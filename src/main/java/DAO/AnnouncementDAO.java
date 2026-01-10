package DAO;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import Cryptographers.RSA;
import Database.ConnectionDB;
import Model.Announcement;
import Model.CryptoKey;
import Model.DecryptedFile;
import Model.User;

public class AnnouncementDAO {
	public void insert(Announcement announcement) throws Exception {
		try (Connection db = new ConnectionDB().connect()) {
			// encryption section
			CryptoKey cryptokey = new CryptoKeyDAO().searchByUser(announcement.getAuthor());
			RSA rsa = new RSA();
			rsa.readPublicKeyString(cryptokey.getPubKey());
			
			String encryptedSubj = rsa.encrypt(announcement.getTitle());
			String encryptedMessage = rsa.encrypt(announcement.getMessage());
			String encryptedFilePath = (announcement.getFilepath() != null) ? 
					rsa.encrypt(rsa.encrypt(announcement.getFilepath().toAbsolutePath()).toString()) :
					null;
			
			// insertion section
			String query = "INSERT INTO announcements "
					+ "(author_id, announcement_title, announcement_message, filepath, sent_at) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?);";
			PreparedStatement stmt = db.prepareStatement(query);
			Timestamp timestamp = announcement.getsentAt();

			stmt.setInt(1, announcement.getAuthor().getId());
			stmt.setString(2, encryptedSubj);
			stmt.setString(3, encryptedMessage);
			stmt.setString(4, encryptedFilePath);
			stmt.setTimestamp(5, timestamp);
			
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public ArrayList<Announcement> searchByDate(java.util.Date date) throws Exception {
		ArrayList<Announcement> result = new ArrayList<Announcement>();
		try (Connection db = new ConnectionDB().connect()) {
			String query = "SELECT "
					+ "u.username, "
					+ "an.announcement_title, "
					+ "an.announcement_message, "
					+ "an.filepath, "
					+ "an.sent_at "
					+ "FROM announcements an "
					+ "INNER JOIN users u ON u.user_id = an.author_id "
					+ "WHERE DATE(an.sent_at) = ?";
			PreparedStatement stmt = db.prepareStatement(query);
			stmt.setDate(1, new Date(date.getTime()));
			
			ResultSet rs = stmt.executeQuery();
			
			RSA rsa = new RSA();
			
			while (rs.next()) {
				String username = rs.getString("username");
				
				// getting user object
				User user = new UserDAO().searchByUsername(username);
				
				CryptoKey cryptokey = new CryptoKeyDAO().searchByUser(user);
				rsa.readPrivateKeyString(cryptokey.getPrivKey());
				
				String title = rsa.decrypt(rs.getString("announcement_title"));
				String message = rsa.decrypt(rs.getString("announcement_message"));

				DecryptedFile file = null;

				String encryptedPath = rs.getString("filepath");
				if (encryptedPath != null && !encryptedPath.isEmpty()) {
				    String decryptedPath = rsa.decrypt(encryptedPath);
				    file = rsa.decrypt(Paths.get(decryptedPath));
				}
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				String sentAtStr = sdf.format(rs.getTimestamp("sent_at"));
				
				result.add(new Announcement(user, title, message, file, sentAtStr));
			}
			
			return result;
		} catch (Exception err) {
			err.printStackTrace();
			throw err;
		}
	}
}
