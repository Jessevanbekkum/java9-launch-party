package nl.ordina.jtech.java9.service.collections.impl.map;

import nl.ordina.jtech.java9.service.collections.SuperMapService;

import java.util.Map;

import static java.util.Map.*;

public class SuperServiceMapOfEntriesImpl implements SuperMapService {
    public Map<String, String> serve() {
        return Map.ofEntries(
                entry("Rosanne", "Ordina JTECH"),
                entry("Bas", "Ordina JTECH"),
                entry("Philippe", "Ordina JTECH"),
                entry("Hedzer", "Ordina JTECH"));
    }
}
