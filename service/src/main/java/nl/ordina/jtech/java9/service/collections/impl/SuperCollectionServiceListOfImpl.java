package nl.ordina.jtech.java9.service.collections.impl;

import nl.ordina.jtech.java9.service.collections.SuperCollectionService;

import java.util.Collection;
import java.util.List;

public class SuperCollectionServiceListOfImpl implements SuperCollectionService {
    public Collection<String> serve() {
        return List.of("you", "just", "got", "served", "using", "list.of");
    }
}