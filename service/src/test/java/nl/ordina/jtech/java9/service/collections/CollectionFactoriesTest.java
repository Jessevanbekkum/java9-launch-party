package nl.ordina.jtech.java9.service.collections;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;

// based on https://github.com/CodeFX-org/demo-java-9/blob/master/src/org/codefx/demo/java9/api/collection_factory_methods/CollectionFactories.java
public class CollectionFactoriesTest {
    private final static Logger LOG = LoggerFactory.getLogger(CollectionFactoriesTest.class);

    /*
     * Fix deze test door gebruik te maken van (immutable!) List.of
     *
     * In Java 8 zou je hiervoor wrapping toepassen middels bijvoorbeeld Collections.unmodifiableList(list)
     */
    @Test(expected = UnsupportedOperationException.class)
    public void verifyThatListsAreImmutable() {
        // setup
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        // verify
        addToCollection(list, "d");
    }

    /*
     * Fix deze test door gebruik te maken van (immutable!) Set.of
     */
    @Test(expected = UnsupportedOperationException.class)
    public void verifyThatSetsAreImmutable() {
        // setup
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");

        // verify
        addToCollection(set, "d");
    }

    /*
     * Fix deze test door gebruik te maken van (immutable!) Map.of
     */
    @Test(expected = UnsupportedOperationException.class)
    public void verifyThatMapsAreImmutable() {
        // setup
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        // put attempt
        map.put("four", 4);
        LOG.info("{} now contains: \n \t{}", getCleanCollectionName(map.entrySet()), map);
    }

    /*
     * Geen nulls toegestaan in List.of() en consorten!
     *
     * Controleer dat het aanmaken van een list met een null element faalt met de bekende NPE
     */
    @Test(expected = NullPointerException.class)
    public void verifyThatElementsMustNotBeNull() {
        // to do: implement
    }

    /*
     * Arrays worden platgeslagen!
     *
     * Controleer dat het aanmaken van een list o.b.v. een array een List met de elementen van de array
     * oplevert. En dus niet een List met 1 element: de array. Er is dus gekozen voor het meest intuitieve gedrag.
     */
    @Test
    public void verifyThatArraysAreFlattened() {
        final String[] array = {"foo", "bar", "baz"};

        // to do: create elements List filled with array elements
        List<String> elements = null;

        assertEquals(3, elements.size());
    }

    /*
     * Geen dupe keys!
     *
     * Duplicate keys in een mutable Map (zoals HashMap) overschrijven de waarde.
     * Map.of echter, levert een immutable op. Alle keys moeten dus meteen goed zijn.
     * Duplicate keys worden dan ook afgekeurd met een IllegalArgumentException.
     *
     * Doe hieronder een Map.of() aanroep die die foutieve situatie triggered.
     */
    @Test(expected = IllegalArgumentException.class)
    public void verifyThatDuplicateKeysAreRejected() {
        // to implement
    }

    /*
     * Set iteration volgorde is ongedefinieerd (die van List wel).
     * De Set.of() implementatie doet dat expliciet door te randomiseren!
     * Onderstaande test zal dus soms falen, soms slagen.
     * Dit is om je aan te leren NOOIT van de volgorde van collecties uit te gaan - juist niet in unit tests.
     * Een mogelijke oplossing is om set.contains() te gebruiken
     */
    @Test
    public void orderIsUnstableAcrossRuns() {
        final List<String> list = null;//List.of("a", "b", "c");
        LOG.info("list: {}", list);
        final Set<String> set = null;//Set.of("a", "b", "c");
        LOG.info("set: {}", set);

        int i = 0;
        for (final String s : set) {
            assertEquals(s, list.get(i++));
        }
    }

    // --------
    // hieronder alleen helper code
    // --------
    private static String joinElementsToString(Collection<?> collection) {
        return collection.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", ", "", "\n"));
    }

    private <T> void addToCollection(Collection<T> collection, T item) {
        String collectionName = getCleanCollectionName(collection);
        try {
            collection.add(item);
            LOG.info("{} is added to {}", item, collectionName);
        } finally {
            LOG.info("{} now contains: \n \t{}", collectionName, collection);
        }
    }

    private <T> String getCleanCollectionName(final Collection<T> collection) {
        return collection.getClass().toString().substring(collection.getClass().toString().lastIndexOf(".") + 1);
    }

}
