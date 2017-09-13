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
        if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) {
            final String[] cmds = new String[]{
                    "cmd",
                    "/c",
                    "tasklist",
                    "/v",
                    "/fo",
                    "csv",
                    "|",
                    "findstr",
                    "/i",
                    "\\\"cmd.exe"};
            Process proc = Runtime.getRuntime().exec(cmds);

            proc.waitFor(30L, TimeUnit.SECONDS);
            InputStream in = proc.getInputStream();
            int available = in.available();
            byte[] outputBytes = new byte[available];

            in.read(outputBytes);
            String pid = new String(outputBytes).split(",")[1];

            LOG.info("Your Windows cmd.exe pid is {}", pid);
        } else {
            final String[] cmds = new String[] {
                    "/bin/sh",
                    "-c",
                    "echo $PPID"
            };
            Process proc = Runtime.getRuntime().exec(cmds);

            if (proc.waitFor() == 0) {
                InputStream in = proc.getInputStream();
                int available = in.available();
                byte[] outputBytes = new byte[available];

                in.read(outputBytes);
                String pid = new String(outputBytes);

                LOG.info("Your Linux/Mac /bin/sh pid is {}", pid);
            }
        }
        throw new RuntimeException("Way too complicated - lees/verwerk het commentaar en verwijder dan deze throw");
    }
}
