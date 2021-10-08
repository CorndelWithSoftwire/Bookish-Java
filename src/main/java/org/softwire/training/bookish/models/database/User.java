package org.softwire.training.bookish.models.database;

public class User {
    private int id;
    private String forename;
    private String surname;

    public int getId() {
        return id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }
}
