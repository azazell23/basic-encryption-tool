package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Database.ConnectionDB;
import Model.CryptoKey;
import Model.User;
import Seeders.Seeder;

public class CryptoKeyDAO {
	public CryptoKey searchByUser(User user) throws Exception{
		try (Connection db = new ConnectionDB().connect()) {
			String query = "SELECT * FROM cryptokeys "
					+ "WHERE owner_id = ?";
			PreparedStatement stmt = db.prepareStatement(query);
			
			stmt.setInt(1, user.getId());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new CryptoKey(
						user, 
						rs.getString("public_key"),
						rs.getString("private_key")
						);
			}
			else {
				throw new Exception("Error: User doesn't have Crypto Key.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void generateForUser(User user) throws Exception {
		try (Connection db = new ConnectionDB().connect()) {
			// generating seed
			Seeder seeder = new Seeder();
			seeder.Generate();
			seeder.generatePrivateKeyString();
			seeder.generatePublicKeyString();
			
			// insertion part
			String query = "INSERT INTO cryptokeys (owner_id, public_key, private_key) "
					+ "VALUES "
					+ "(?, ?, ?)";
			PreparedStatement stmt = db.prepareStatement(query);
			
			stmt.setInt(1, user.getId());
			stmt.setString(2, seeder.getPublicKeyString());
			stmt.setString(3, seeder.getPrivateKeyString());
			
			stmt.executeUpdate();
		} catch (Exception err) {
			err.printStackTrace();
			throw err;
		}
		
	}
}
