package Seeders;

import java.math.BigInteger;
import java.security.SecureRandom;

public class NumberGenerator {
    public static BigInteger generate(BigInteger min, SecureRandom randomEngine)
    {
        BigInteger result = new BigInteger(min.bitLength(), randomEngine);
        return result.add(min);
    }

    public static BigInteger generate(BigInteger min, BigInteger max, SecureRandom randomEngine)
    {
        BigInteger range = max.subtract(min);
        BigInteger result;
        do {
            result = new BigInteger(range.bitLength(), randomEngine);
        } while (result.compareTo(range) >= 0);

        return result.add(min);
    }
}