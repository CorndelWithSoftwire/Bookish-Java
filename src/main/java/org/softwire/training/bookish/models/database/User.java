package org.softwire.training.bookish.models.database;

import java.util.HashSet;
import java.util.Set;

public class User {
    private int id;
    private String forename;
    private String surname;
    private Set<CopyRegistry> copyRegistrySet;

    public User(){
        id = 0;
        forename = "";
        surname = "";
        copyRegistrySet = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public void addCopyRegistry(CopyRegistry copyRegistry) {
        copyRegistrySet.add(copyRegistry); // duplicates ignored
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", copyRegistrySet=" + copyRegistrySet +
                '}';
    }
}
