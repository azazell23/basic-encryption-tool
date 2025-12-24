package Model;

import java.nio.file.Path;
import java.sql.Time;
import java.sql.Timestamp;

public class Announcement {
    private int id;
    private User author;
    private String title;
    private String message;
    private Path filepath;
    
    public Announcement(User user, String title, String message, Path filePath) {
    		this.author = user;
    		this.title = title;
    		this.message = message;
    		this.filepath = filePath;
    }
    
}
