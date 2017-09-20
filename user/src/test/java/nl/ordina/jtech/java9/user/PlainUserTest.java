package nl.ordina.jtech.java9.user;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PlainUserTest {

    /**
     * Maak deze test slagend door in de User interface een extract method te doen op de String concatenaties in User.getName().
     * Noem de nieuwe method bijvoorbeeld 'convertPretty'.
     * Markeer daarna de extracted methode als private, en verwijder de 'default' modifier.
     *
     * Je hebt nu een private method in de interface gemaakt, die alleen vanuit de (public) default methods is aan te roepen.
     * Op deze manier kun je - in java 9 - zelfs implementatiedetails van code in interfaces verbergen!
     *
     * Als laatste fix je de test door simpelweg een spatie toe te voegen in de private method convertPretty().
     */
    @Test
    public void shouldGetPrettyUserName() {
        final PlainUser user = new PlainUser();
        user.setLastName("Gosling");
        user.setFirstName("James");
        assertEquals("James Gosling", user.getName());
    }

}