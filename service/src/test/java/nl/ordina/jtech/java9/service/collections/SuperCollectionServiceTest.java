package nl.ordina.jtech.java9.service.collections;

import nl.ordina.jtech.java9.service.collections.impl.SuperCollectionServiceArrayListImpl;
import nl.ordina.jtech.java9.service.collections.impl.internal.SuperCollectionServiceArraysAsListInternal;
import org.junit.Test;

public class SuperCollectionServiceTest {
    private static final int MAX = 5_000_000;

    /*
     * De ArrayList implementatie is minder interessant nu we List.of() hebben.
     * Jouw opdracht:
     * 1. markeer SuperCollectionServiceArrayListImpl met @Deprecated(since = "1.9", forRemoval = true)
     * 2. Bij compilatie zie je een deprecation warning
     * 3. die lossen we natuurlijk op met @SuppressWarnings("deprecation")
     * 4. of niet?
     * 5. .. aangezien we 'forRemoval=true' aangeven, moet je in dit geval onderdrukken met:
     * @SuppressWarnings({"deprecation", "removal"})
     * 6. Compileert alles weer zonder warnings, dan mag je de 'throw new RuntimeException' hieronder verwijderen
     */
    @Test
    public void loadTestArrayListFromArray() {
        SuperCollectionService service = new SuperCollectionServiceArrayListImpl();
        for (int i = 0; i < MAX; i++) {
            service.serve();
        }
        throw new RuntimeException("zie commentaar/instructies over @Deprecated - daarna deze throw verwijderen");
    }

    /*
     * Deze test slaagt al - toont ArrayList. Komt terug bij modularisatie!
     */
    @Test
    public void loadTestArrayList() {
        SuperCollectionService service = new SuperCollectionServiceArraysAsListInternal();
        for (int i = 0; i < MAX; i++) {
            service.serve();
        }
    }
}
