package nl.ordina.jtech.java9.service.collections.impl.map;

import nl.ordina.jtech.java9.service.collections.SuperMapService;

import java.util.Map;

public class SuperServiceMapOfImpl implements SuperMapService {
    public Map<String, String> serve() {
        return Map.of(
                "Rosanne", "Ordina JTECH",
                "Bas", "Ordina JTECH",
                "Philippe", "Ordina JTECH",
                "Hedzer", "Ordina JTECH"
        );
    }
}
