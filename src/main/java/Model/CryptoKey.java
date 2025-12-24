package Model;

public class CryptoKey {
    private int id;
    private User user;
    private String pubKey;
    private String privKey;

    public CryptoKey(User user, String pubKey, String privKey) {
        this.user = user;
        this.pubKey = pubKey;
        this.privKey = privKey;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPubKey() {
        return this.pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    public String getPrivKey() {
        return this.privKey;
    }

    public void setPrivKey(String privKey) {
        this.privKey = privKey;
    }


}