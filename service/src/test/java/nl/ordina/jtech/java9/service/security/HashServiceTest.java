package nl.ordina.jtech.java9.service.security;

import org.junit.Test;

public class HashServiceTest {
    /*
     * In Java 9 is een nieuw algoritme toegevoegd: SHA3-512.
     * Hiermee kunnen 2x zo lange hashes (512 bits) berekend worden.
     *
     * SHA-256 is al wel bekend in Java 8.
     *
     * Maak de test slagend door het juiste algoritme op te voeren.
     */
    @Test
    public void testAvailableHashAlgorithms() throws Exception {
        HashService.hash("test", "SHA-256");

        HashService.hash("test", "foo");
    }

}