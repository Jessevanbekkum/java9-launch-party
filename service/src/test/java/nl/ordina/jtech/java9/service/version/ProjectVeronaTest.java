package nl.ordina.jtech.java9.service.version;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class ProjectVeronaTest {
    private final static Logger LOG = LoggerFactory.getLogger(ProjectVeronaTest.class);

    @Test
    public void shouldGetParsableVersionNumbers() {
        // In Java 9 you would obviously use List.of() in stead of Arrays.asList()
        //List.of("java.version", "java.runtime.version", "java.vm.version", "java.specification.version", "java.vm.specification.version")
        Arrays.asList("java.version", "java.runtime.version", "java.vm.version", "java.specification.version", "java.vm.specification.version")
                .stream().forEach(prop ->
                LOG.info("{}: {}", prop, System.getProperty(prop)));

        // tests asserts will pass when running the test on a Java 1.8[.0] VM
        // your task: update them to pass on the Java 9 VM. Check the console log for appropriate values.
        assertTrue(System.getProperty("java.version").contains("1.8.0"));
        assertTrue(System.getProperty("java.runtime.version").contains("1.8.0"));
        assertTrue(System.getProperty("java.specification.version").contains("1.8"));
        assertTrue(System.getProperty("java.vm.specification.version").contains("1.8"));
    }
}
