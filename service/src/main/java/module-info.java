module service {
    requires slf4j.api;

    exports nl.ordina.jtech.java9.service;
    exports nl.ordina.jtech.java9.service.collections;
    exports nl.ordina.jtech.java9.service.collections.impl;

    opens nl.ordina.jtech.java9.service.collections.impl.internal;
}