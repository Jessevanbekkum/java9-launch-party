package nl.ordina.jtech.java9.service.collections;

import nl.ordina.jtech.java9.service.collections.impl.map.SuperServiceHashMapImpl;
import nl.ordina.jtech.java9.service.collections.impl.map.SuperServiceMapOfEntriesImpl;
import nl.ordina.jtech.java9.service.collections.impl.map.SuperServiceMapOfImpl;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SuperMapServiceTest {
    private static final int MAX = 5_000_000;

    @Test
    public void loadTestOldHashMap() {
        SuperMapService service = new SuperServiceHashMapImpl();
        loadTest(service);
    }

    @Test
    public void loadTestMapOf() {
        SuperMapService service = new SuperServiceMapOfImpl();
        loadTest(service);
    }

    @Test
    public void loadTestMapOfEntries() {
        SuperMapService service = new SuperServiceMapOfEntriesImpl();
        loadTest(service);
    }

    private void loadTest(final SuperMapService service) {
        for (int i = 0; i < MAX; i++) {
            assertEquals(4, service.serve().size());
        }
    }

}
