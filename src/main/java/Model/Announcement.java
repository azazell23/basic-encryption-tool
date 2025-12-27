package Model;

import java.nio.file.Path;
import java.sql.Timestamp;

public class Announcement {
    private int id;
    private User author;
    private String title;
    private String message;
    private Path filepath;
    private Timestamp sentAt;
    private String sentAtStr;
    private DecryptedFile decryptedFile;
    
    public Announcement(User user, String title, String message, Path filePath, Timestamp sentAt) {
    		this.author = user;
    		this.title = title;
    		this.message = message;
    		this.filepath = filePath;
    		this.sentAt = sentAt;
    }
    
    public Announcement(User user, String title, String message, DecryptedFile file, String sentAtStr) {
		this.author = user;
		this.title = title;
		this.message = message;
		this.decryptedFile = file;
		this.sentAtStr = sentAtStr;
}

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Path getFilepath() {
        return this.filepath;
    }

    public void setFilepath(Path filepath) {
        this.filepath = filepath;
    }
    
    public Timestamp getsentAt() {
    		return this.sentAt;
    }
    
    public void setsentAt(Timestamp sentAt) {
    		this.sentAt = sentAt;
    }
    
    public String getSentAtStr() {
    		return this.sentAtStr;
    }
    
    public DecryptedFile getDecryptedFile() {
    		return this.decryptedFile;
    }
}
