package Model;

public class DecryptedFile {
	private String fileName;
    private byte[] data;

    public DecryptedFile(String fileName, byte[] data) {
        this.fileName = fileName;
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getData() {
        return data;
    }
}
