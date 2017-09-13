package nl.ordina.jtech.java9.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReader {
    private final static Logger LOG = LoggerFactory.getLogger(FileReader.class);

    public static void main(String[] args) throws FileNotFoundException {
        char[] chars = new char[131];

        final InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testFile.txt");

        try (InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream, "UTF-8")) {
            inputStreamReader.read(chars);
            LOG.info("read {} ", String.valueOf(chars));
        } catch (IOException e) {
            LOG.error("I/O problem", e);
        }
    }
}
