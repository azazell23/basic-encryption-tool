package Seeders;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
 
public class Seeder {
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger e;
    private BigInteger phi;
    private BigInteger d;
    private String publicKeyString;
    private String privateKeyString;
    
    public String getPublicKeyString() {
    		return publicKeyString;
    }
    
    public String getPrivateKeyString() {
    		return privateKeyString;
    }

    public void generatePublicKeyString()
    {
        try {
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(n, e);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = factory.generatePublic(keySpec);
            byte[] packedKey = pubKey.getEncoded();
            publicKeyString = Base64.getEncoder().encodeToString(packedKey);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void generatePrivateKeyString()
    {
        try {
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(n, d);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PrivateKey privKey = factory.generatePrivate(keySpec);

            byte[] packedKey = privKey.getEncoded();
            privateKeyString = Base64.getEncoder().encodeToString(packedKey);
        } catch (Exception e) {
        		System.out.println(e);
        }
    }

    public void Generate()
    {
        SecureRandom rand = new SecureRandom();
        do {
            p = PrimeGenerator.generate(rand);
            q = PrimeGenerator.generate(rand);
        } while (p == q);
        
        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        do {
            e = NumberGenerator.generate(BigInteger.valueOf(2), phi, rand);
        } while (!e.gcd(phi).equals(BigInteger.ONE));

        d = e.modInverse(phi);
    }
}