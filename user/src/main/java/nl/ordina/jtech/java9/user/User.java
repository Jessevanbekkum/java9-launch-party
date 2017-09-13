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

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setLastName(String lastName) {
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
