package nl.ordina.jtech.java9.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlainUser implements User {
    private final static Logger LOG = LoggerFactory.getLogger(PlainUser.class);

    private String firstName;
    private String lastName;

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }
}
