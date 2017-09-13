package nl.ordina.jtech.java9.user;

public interface BaseUser {

    void setFirstName(String firstName);
    void setLastName(String lastName);
    String getFirstName();
    String getLastName();

    default String getFirstName(BaseUser user){
        return convertPretty(user.getFirstName(), user.getLastName());
    }

    //This default behavior is wanted in the interface
    private String convertPretty(final String name, final String lastName) {
        return name + " " + lastName;
    }

    default String getName(BaseUser user) {return "";} // TODO Bas: deze moet nog uitgeprogd worden. Heb dit toegevoegd zodat't iig compiled.
}
