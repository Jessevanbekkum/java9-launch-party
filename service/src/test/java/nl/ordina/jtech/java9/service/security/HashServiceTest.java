package nl.ordina.jtech.java9.service.security;

import org.junit.Test;

public class HashServiceTest {
    @Test
    public void testAvailableHashAlgorithms() throws Exception {
        HashService.hash("test", "SHA-256");

        HashService.hash("test", "SHA3-512");
    }

}