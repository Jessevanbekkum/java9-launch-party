package nl.ordina.jtech.java9.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class ProcessApiTest {
    private final static Logger LOG = LoggerFactory.getLogger(ProcessApiTest.class);

    /*
     * Onderstaande wordt simpelweg:
        final String cmd = (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) ? "cmd.exe" : "/bin/sh";
        Process proc = Runtime.getRuntime().exec(cmd);
        LOG.info("Your {} pid is {}", cmd, proc.toHandle().pid());
     *
     * Je kunt nog meer informatie opvragen met bijv.
     * LOG.info("executable: {}", ProcessHandle.current().info().command());
     * LOG.info("user: {}", ProcessHandle.current().info().user());
     * (dit vraagt info op van het JVM proces)
     *
     * Merk op dat de info() elementen allemaal van type Optional<T> zijn.
     */
    @Test
    public void startAShell() throws IOException, InterruptedException {
        final String cmd = (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) ? "cmd.exe" : "/bin/sh";
        Process proc = Runtime.getRuntime().exec(cmd);
        LOG.info("Your {} pid is {}", cmd, proc.toHandle().pid());
    }
}
