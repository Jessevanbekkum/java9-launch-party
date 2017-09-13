package nl.ordina.jtech.java9.service.collections.impl.map;

import nl.ordina.jtech.java9.service.collections.SuperMapService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SuperServiceHashMapImpl implements SuperMapService {
    public Map<String, String> serve() {
        Map<String, String> map = new HashMap<>(3);
        map.put("Rosanne", "Ordina JTECH");
        map.put("Bas", "Ordina JTECH");
        map.put("Hedzer", "Ordina JTECH");
        return Collections.unmodifiableMap(map);
    }
}
