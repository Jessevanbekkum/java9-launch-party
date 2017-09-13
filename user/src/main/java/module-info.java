module user {
    requires service;

    requires slf4j.api; // automodule based on file name slf4j-api-1.7.5.jar
    requires spring.context; // automodule based on file name spring-context-4.3.9.RELEASE.jar

    // transitive dep required by spring.context; must define here because of automodule
    requires java.sql;
}
