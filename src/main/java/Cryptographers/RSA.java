package Cryptographers;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

import Model.DecryptedFile;

public class RSA {
    private BigInteger e;
    private BigInteger n;
    private BigInteger d;

    public void readPublicKeyString(String keyString) throws Exception
    {
        try {
            byte[] packedKey = Base64.getDecoder().decode(keyString);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(packedKey);
            KeyFactory factory = KeyFactory.getInstance("RSA");

            PublicKey pubKey = factory.generatePublic(keySpec);
            RSAPublicKey rsaKey = (RSAPublicKey) pubKey;
            n = rsaKey.getModulus();
            e = rsaKey.getPublicExponent();
        } catch (Exception e) {
            throw new Exception("Error: Failed to read public key.");
        }
    }

    public void readPrivateKeyString(String keyString) throws Exception
    {
        try {
            byte[] packedKey = Base64.getDecoder().decode(keyString);
        
            // FIX 1: Use PKCS8EncodedKeySpec for Private Keys
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(packedKey);
            
            KeyFactory factory = KeyFactory.getInstance("RSA");

            PrivateKey privKey = factory.generatePrivate(keySpec);
            
            // FIX 2: Cast to RSAPrivateKey (not RSAPublicKey)
            RSAPrivateKey rsaKey = (RSAPrivateKey) privKey;
            
            n = rsaKey.getModulus();
        
            d = rsaKey.getPrivateExponent();
        } catch (Exception e) {
            throw new Exception("Error: Failed to read private key.");
        }
    }
    
    private int getMaxPlaintextBlockSize() {
        return (n.bitLength() / 8) - 1;
    }

    public Path encrypt(Path filePath) throws Exception {
        if (!Files.exists(filePath)) {
            throw new Exception("Error: Couldn't read file.");
        }

        byte[] fileBytes = Files.readAllBytes(filePath);
        int blockSize = getMaxPlaintextBlockSize();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // --- encrypt in chunks ---
        for (int i = 0; i < fileBytes.length; i += blockSize) {
            int len = Math.min(blockSize, fileBytes.length - i);
            byte[] block = Arrays.copyOfRange(fileBytes, i, i + len);

            BigInteger m = new BigInteger(1, block);
            BigInteger c = m.modPow(e, n);

            byte[] encBlock = c.toByteArray();

            // store encrypted block length (2 bytes)
            out.write((encBlock.length >>> 8) & 0xFF);
            out.write(encBlock.length & 0xFF);

            // store encrypted block
            out.write(encBlock);
        }

        // --- output file ---
        String originalName = filePath.getFileName().toString();

        Path targetDir = Paths.get("enc").toAbsolutePath();

        Path output = targetDir.resolve(originalName + ".enc");
        Files.write(output, out.toByteArray());

        return output;
    }


    public DecryptedFile decrypt(Path filePath) throws Exception {
        if (!Files.exists(filePath)) {
            throw new Exception("Error: Couldn't read file.");
        }

        byte[] encrypted = Files.readAllBytes(filePath);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int i = 0;

        // --- decrypt in chunks ---
        while (i < encrypted.length) {
            int len = ((encrypted[i] & 0xFF) << 8) | (encrypted[i + 1] & 0xFF);
            i += 2;

            byte[] encBlock = Arrays.copyOfRange(encrypted, i, i + len);
            i += len;

            BigInteger c = new BigInteger(1, encBlock);
            BigInteger m = c.modPow(d, n);

            byte[] block = m.toByteArray();

            // remove sign byte if present
            if (block.length > 1 && block[0] == 0) {
                block = Arrays.copyOfRange(block, 1, block.length);
            }

            out.write(block);
        }

        // --- restore original filename ---
        String name = filePath.getFileName().toString();
        String originalName = name.replaceFirst("\\.enc$", "");

        return new DecryptedFile(originalName, out.toByteArray());
    }


    public String encrypt(String string_) throws Exception {
        byte[] data = string_.getBytes(StandardCharsets.UTF_8);
        int blockSize = getMaxPlaintextBlockSize();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        for (int i = 0; i < data.length; i += blockSize) {
            int len = Math.min(blockSize, data.length - i);
            byte[] block = Arrays.copyOfRange(data, i, i + len);

            BigInteger m = new BigInteger(1, block);
            BigInteger c = m.modPow(e, n);

            byte[] encBlock = c.toByteArray();

            // store encrypted block length (2 bytes)
            out.write((encBlock.length >>> 8) & 0xFF);
            out.write(encBlock.length & 0xFF);

            // store encrypted block
            out.write(encBlock);
        }

        return Base64.getEncoder().encodeToString(out.toByteArray());
    }

    public String decrypt(String ciphertext_) throws Exception {
        byte[] encrypted = Base64.getDecoder().decode(ciphertext_);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int i = 0;
        while (i < encrypted.length) {
            int len = ((encrypted[i] & 0xFF) << 8) | (encrypted[i + 1] & 0xFF);
            i += 2;

            byte[] encBlock = Arrays.copyOfRange(encrypted, i, i + len);
            i += len;

            BigInteger c = new BigInteger(1, encBlock);
            BigInteger m = c.modPow(d, n);

            byte[] block = m.toByteArray();

            // remove sign byte if present
            if (block.length > 1 && block[0] == 0) {
                block = Arrays.copyOfRange(block, 1, block.length);
            }

            out.write(block);
        }

        return new String(out.toByteArray(), StandardCharsets.UTF_8);
    }

}
