package nl.ordina.jtech.java9.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashService {
    private final static Logger LOG = LoggerFactory.getLogger(HashService.class);

    public static String hash(String message, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(message.getBytes());
        byte[] messageDigestMD5 = messageDigest.digest();
        StringBuilder stringBuilder = new StringBuilder();
        for (byte bytes : messageDigestMD5) {
            stringBuilder.append(String.format("%02x", bytes & 0xff));
        }

        LOG.info("data: {} digested with {}: {}", message, algorithm, stringBuilder);
        return stringBuilder.toString();
    }
}
