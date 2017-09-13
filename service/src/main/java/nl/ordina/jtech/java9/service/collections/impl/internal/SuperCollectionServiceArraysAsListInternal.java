package nl.ordina.jtech.java9.service.collections.impl.internal;

import nl.ordina.jtech.java9.service.collections.SuperCollectionService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class SuperCollectionServiceArraysAsListInternal implements SuperCollectionService {
    public Collection<String> serve() {
        return Collections.unmodifiableCollection(Arrays.asList("you","just","got","served","an", "array", "as", "list"));
    }
}
