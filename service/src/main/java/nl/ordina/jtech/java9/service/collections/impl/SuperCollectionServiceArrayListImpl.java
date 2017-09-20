package nl.ordina.jtech.java9.service.collections.impl;

import nl.ordina.jtech.java9.service.collections.SuperCollectionService;

import java.util.*;

@Deprecated(since = "1.9", forRemoval = true)
public class SuperCollectionServiceArrayListImpl implements SuperCollectionService {
    public Collection<String> serve() {
        return Collections.unmodifiableList(new ArrayList<>(Arrays.asList("you","just","got","served","an","unmodifiable", "arraylist")));
    }
}
