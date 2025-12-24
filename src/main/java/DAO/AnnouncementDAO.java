package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Database.ConnectionDB;
import Model.Announcement;

public class AnnouncementDAO {
	public void insert(Announcement announcement) throws Exception {
		try (Connection db = new ConnectionDB().connect()) {
			String query = "INSERT INTO announcements "
					+ "(author_id, announcement_title, announcement_message, filepath, sent_at) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?);";
			PreparedStatement stmt = db.prepareStatement(query);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
