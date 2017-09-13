package nl.ordina.jtech.java9.service.collections;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.entry;
import static junit.framework.TestCase.assertEquals;

// based on https://github.com/CodeFX-org/demo-java-9/blob/master/src/org/codefx/demo/java9/api/collection_factory_methods/CollectionFactories.java
public class CollectionFactoriesTest {
    private final static Logger LOG = LoggerFactory.getLogger(CollectionFactoriesTest.class);

    @Test
    public void verifyThatWeCanCreateVariousCollections() {
        List<String> list = List.of("a", "b", "c");
        Set<String> set = Set.of("a", "b", "c");
        Map<String, Integer> mapImmediate = Map.of(
                "one", 1,
                "two", 2,
                "three", 3);
        Map<String, Integer> mapEntries = Map.ofEntries(
                entry("one", 1),
                entry("two", 2),
                entry("three", 3));

        Stream.of(list, set, mapImmediate.entrySet(), mapEntries.entrySet())
                .map(CollectionFactoriesTest::joinElementsToString)
                .forEach(LOG::info);
    }

    public static String joinElementsToString(Collection<?> collection) {
        return collection.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", ", "", "\n"));
    }

    @Test(expected = NullPointerException.class)
    public void verifyThatElementsMustNotBeNull() {
        List.of(null);
    }

    @Test
    public void verifyThatArraysAreFlattened() {
        final String[] array = {"foo", "bar", "baz"};

        // to do: create elements List filled with array elements
        List<String> elements = List.of(array);

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
        Map.of(
                "aap", "foo",
                "noot", "bar",
                "mies", "baz",
                "mies", "fnord"
                );
    }

    @Test(expected = UnsupportedOperationException.class)
    public void verifyThatListOfIsImmutable() {
        List.of("a", "b", "c").add("d");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void verifyThatSetOfIsImmutable() {
        Set.of("a", "b", "c").add("d");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void verifyThatMapOfIsImmutable() {
        Map.ofEntries(entry("one", 1)).entrySet().add(entry("two", 2));
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
        final List<String> list = List.of("a", "b", "c");
        LOG.info("list: {}", list);
        final Set<String> set = Set.of("a", "b", "c");
        LOG.info("set: {}", set);

        int i = 0;
        for (final String s : set) {
            assertEquals(s, list.get(i++));
        }
    }
}
