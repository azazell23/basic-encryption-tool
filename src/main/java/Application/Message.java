package Application;

import java.sql.Time;
import java.sql.Timestamp;

public class Message {
    private int id;
    private User author;
    private User receiver;
    private Time sendingTime;
    private String ciphertext;
    private Timestamp timestamp;


    public Message(int id, User author, User receiver, Time sendingTime, String ciphertext, Timestamp timestamp) {
        this.id = id;
        this.author = author;
        this.receiver = receiver;
        this.sendingTime = sendingTime;
        this.ciphertext = ciphertext;
        this.timestamp = timestamp;
    }

    public void storeToDB()
    {

    }
    
}
