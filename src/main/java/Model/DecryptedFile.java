package Model;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    
    public void downloadFile() throws Exception{
    		try {
    			Path targetDir = Paths.get("downloads").toAbsolutePath();
    			if (targetDir == null) {
    				targetDir = Paths.get(".");
    			}

    			Path output = targetDir.resolve(this.fileName);
    			Files.write(output, data);
    		} catch (Exception err) {
    			err.printStackTrace();
    			throw err;
    		}
    }
}
