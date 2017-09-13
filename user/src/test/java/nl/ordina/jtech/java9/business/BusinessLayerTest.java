package nl.ordina.jtech.java9.business;

import nl.ordina.jtech.java9.user.User;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BusinessLayerTest {
    @Test
    public void shouldBeAbleToAccessInternalServe() {
        final String userName = BusinessLayer.internalServe(new User());

        assertTrue(userName.endsWith("[you, just, got, served, an, array, as, list]"));
    }

    @Test
    public void shouldBeAbleToAccessNormalServe() {
        final String userName = BusinessLayer.normalServe(new User());

        assertTrue(userName.endsWith("[you, just, got, served, an, unmodifiable, arraylist]"));
    }
}