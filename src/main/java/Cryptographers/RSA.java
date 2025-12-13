package Cryptographers;

import java.io.IOException;
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
import java.util.Base64;

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

    public void encrypt(Path filePath)
    {
        if (Files.exists(filePath))
        {
            try {
                byte[] fileBytes = Files.readAllBytes(filePath);
                BigInteger m = new BigInteger(1, fileBytes);
                BigInteger c = m.modPow(e, n);
                byte[] byteArr = c.toByteArray();
                Files.write(filePath.getParent().toAbsolutePath().normalize().resolve("test.enc"), byteArr);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            }
            
        }
    }

    public void decrypt(Path filePath) throws Exception
    {
        if (Files.exists(filePath))
        {
            try {
                byte[] fileBytes = Files.readAllBytes(filePath);
                BigInteger c = new BigInteger(1, fileBytes);
                BigInteger m = c.modPow(d, n);
                byte[] byteArr = m.toByteArray();
                Files.write(filePath.getParent().toAbsolutePath().normalize().resolve("testDecrpted.txt"), byteArr);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            }
        }
    }

    public String encrypt(String string_) throws Exception
    {
        try {
            byte[] textByte = string_.getBytes();
            BigInteger m = new BigInteger(1, textByte);
            BigInteger c = m.modPow(e, n);
            byte[] byteArr = c.toByteArray();
            return Base64.getEncoder().encodeToString(byteArr);
        } catch (Exception e) {
            throw e;
        }
    }

    public String decrypt(String ciphertext_) throws Exception
    {
        try {
            byte[] cipherByte = Base64.getDecoder().decode(ciphertext_);

            BigInteger c = new BigInteger(1, cipherByte);
            BigInteger m = c.modPow(d, n);

            byte[] byteArr = m.toByteArray();

            return new String(byteArr);
        } catch (Exception e) {
            throw e;
        }
    }
}
