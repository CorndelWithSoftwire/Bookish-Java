package org.softwire.training.bookish.models.database;

public class Author {
    private final String name;
    private final int id;


    public Author(String authorName, int increment) {
        this.name = authorName;
        this.id = increment;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
