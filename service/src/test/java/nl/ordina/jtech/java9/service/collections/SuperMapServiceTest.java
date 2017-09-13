package nl.ordina.jtech.java9.service.collections;

import nl.ordina.jtech.java9.service.collections.impl.map.SuperServiceHashMapImpl;
import org.junit.Test;

import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class SuperMapServiceTest {
    private static final int MAX = 5_000_000;

    /*
     * Deze test slaagt al - toont hoe we in Java 8 HashMap.put() gebruiken.
     */
    @Test
    public void loadTestOldHashMap() {
        SuperMapService service = new SuperServiceHashMapImpl();
        loadTest(service);
    }

    /*
     * In deze test ga je werken met Map.of(..) waarmee je eenvoudig immutable Map instanties maakt.
     *
     * Implementeer de interface SuperMapService.
     * Maak daarbij gebruik van de overload Map.of(key1,value1, key2,value2, key3,value3)
     * De waarde van keys&values maakt voor deze test niet uit - zorg wel dat het er precies 3 zijn.
     */
    @Test
    public void loadTestMapOf() {
        SuperMapService service = //new SuperServiceMapOfImpl();
            new SuperMapService() { @Override public Map<String, String> serve() {
                throw new IllegalArgumentException("not implemented - create SuperServiceMapOfImpl");
            }};
        loadTest(service);
    }

    /*
     * In deze test ga je werken met Map.ofEntries(..) waarmee je eenvoudig immutable Map instanties maakt.
     *
     * Implementeer de interface SuperMapService.
     * Maak daarbij gebruik van de overload Map.ofEntries(entry(key,value), ..)
     * De waarde van keys&values maakt voor deze test niet uit - zorg wel dat het er precies 3 zijn.
     * Tip: voeg een static import toe op java.util.Map.*
     */
    @Test
    public void loadTestMapOfEntries() {
        SuperMapService service = //new SuperServiceMapOfEntriesImpl();
            new SuperMapService() { @Override public Map<String, String> serve() {
                throw new IllegalArgumentException("not implemented - create SuperServiceMapOfEntriesImpl");
            }};
        loadTest(service);
    }

    // --------
    // hieronder alleen helper code
    // --------
    private void loadTest(final SuperMapService service) {
        for (int i = 0; i < MAX; i++) {
            assertEquals(3, service.serve().size());
        }
    }
}
