package nl.ordina.jtech.java9.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class TryWithResourcesTest {
    private final static Logger LOG = LoggerFactory.getLogger(TryWithResourcesTest.class);

    /*
     * Na conversie naar Java 9 is de lokale variabele inputStreamReader redundant; je kunt in de try(..)
     * rechtstreeks verwijzen naar _inputStreamReader.
     * Dit is mogelijk vanwege de Milling Coin feature "Try-with-resources enhancement".
     * Doe je dit in Java 8 dan volgt de melding 'Resource references are not supported at this language level' in IntellliJ;
     * de Java compiler geeft iets concreter aan: 'variables in try-with-resources not supported in -source 1.8'
     *
     * Na het aanpassen fix je de test eenvoudig door in de assert 9 te vervangen in 7.
     */
    @Test
    public void shouldReadFromStream() throws IOException {
        char[] chars = new char[131];

        final InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testFile.txt");

        final InputStreamReader _inputStreamReader = new InputStreamReader(resourceAsStream, "UTF-8");
        try (InputStreamReader inputStreamReader = _inputStreamReader) {
            inputStreamReader.read(chars);
            final String lineRead = String.valueOf(chars);
            LOG.info("read: '{}'", lineRead);
            assertTrue(lineRead.startsWith("In Java 9, "));
        }
    }
}
