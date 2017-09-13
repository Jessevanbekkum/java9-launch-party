package nl.ordina.jtech.java9.service.collections;

import nl.ordina.jtech.java9.service.collections.impl.SuperCollectionServiceArrayListImpl;
import nl.ordina.jtech.java9.service.collections.impl.SuperCollectionServiceListOfImpl;
import nl.ordina.jtech.java9.service.collections.impl.internal.SuperCollectionServiceArraysAsListInternal;
import org.junit.Test;

public class SuperCollectionServiceTest {
    private static final int MAX = 5_000_000;

    @Test
    public void loadTestListOf() {
        SuperCollectionService service = new SuperCollectionServiceListOfImpl();
        for (int i = 0; i < MAX; i++) {
            service.serve();
        }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void loadTestArrayListFromArray() {
        SuperCollectionService service = new SuperCollectionServiceArrayListImpl();
        for (int i = 0; i < MAX; i++) {
            service.serve();
        }
    }

    @Test
    public void loadTestArrayList() {
        SuperCollectionService service = new SuperCollectionServiceArraysAsListInternal();
        for (int i = 0; i < MAX; i++) {
            service.serve();
        }
    }
}
