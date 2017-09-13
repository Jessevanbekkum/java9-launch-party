package nl.ordina.jtech.java9.user;

import nl.ordina.jtech.java9.business.BusinessLayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class User implements BaseUser {
    private final static Logger LOG = LoggerFactory.getLogger(User.class);

    private String firstName;
    private String lastName;

    public static void main(String[] args) throws IOException {
        final User user = new User();
        user.setLastName("Gosling");
        user.setFirstName("James");
        user.serve();
    }

    public void serve() {
        LOG.info("normalServe result: {}", BusinessLayer.normalServe(this));
        LOG.info("internalServe result: {}", BusinessLayer.internalServe(this));
    }

    //This default behavior is wanted in the interface
    private String convertPretty(final String name, final String lastName) {
        return name + " " + lastName;
    }

    public String getName() {
        return convertPretty(firstName, lastName);
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
}
