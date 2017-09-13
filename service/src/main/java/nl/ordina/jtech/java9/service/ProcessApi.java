package nl.ordina.jtech.java9.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessApi {
    private final static Logger LOG = LoggerFactory.getLogger(ProcessApi.class);

    public static void main(String[] args) throws Exception {
        final String cmd = (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) ? "cmd.exe" : "/bin/sh";
        Process proc = Runtime.getRuntime().exec(cmd);
        LOG.info("Your {} pid is {}", cmd, proc.toHandle().pid());

        LOG.info("executable: {}", proc.toHandle().info().command().get());
        LOG.info("user: {}", proc.toHandle().info().user());
    }
}
