module service {
    exports nl.ordina.jtech.java9.service.collections;
    exports nl.ordina.jtech.java9.service.collections.impl;

    opens nl.ordina.jtech.java9.service.collections.impl.internal;

    requires slf4j.api;
}
