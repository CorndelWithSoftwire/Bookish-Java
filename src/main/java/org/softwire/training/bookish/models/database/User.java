package org.softwire.training.bookish.models.database;

public class User {
    private int id;
    private String name;
    private String password;
    private String type;
    private int active;

    public User(int id, String name, String password, String type, int active){
        this.id = id;
        this.name = name;
        this.password = password;
        this.type = type;
        this.active = active;
    }

    public int getId(){return this.id;}
    public String getName(){return this.name;}
    public String getPassword(){return this.password;}
    public String getType(){return this.type;}
    public int getActive(){return this.active;}
    public void setId(int updated){this.id = updated;}
    public void setName(String updated){this.name = updated;}
    public void setPassword(String updated){this.password = updated;}
    public void setType(String updated){this.type = updated;}
    public void setActive(int updated){this.active = updated;}
}
