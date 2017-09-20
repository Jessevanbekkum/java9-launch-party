package nl.ordina.jtech.java9.user;

public interface User {
    void setLastName(final String lastName);
    void setFirstName(final String firstName);
    String getLastName();
    String getFirstName();

    default String getName() {
        return getFirstName() + "" + getLastName();
    }

}
