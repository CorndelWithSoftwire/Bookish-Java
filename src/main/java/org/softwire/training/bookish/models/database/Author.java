package org.softwire.training.bookish.models.database;

public class Author {
    private final String authorName;
    private final int id;


    public Author(String authorName, int increment) {
        this.authorName = authorName;
        this.id = increment;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + authorName + '\'' +
                ", id=" + id +
                '}';
    }
}


