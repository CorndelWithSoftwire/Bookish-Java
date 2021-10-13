package org.softwire.training.bookish.models.database;

import java.util.Objects;

public class Author {
    private  String authorName;
    private  int authorId;


    public Author(int increment,String authorName) {
        this.authorId = increment;
        this.authorName = authorName;
    }

    public Author(){};

    public String getAuthorName() {
        return authorName;
    }

    public int getAuthorId() {
        return authorId;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + authorName + '\'' +
                ", id=" + authorId +
                '}';
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return authorName.equals(author.authorName);
    }

    @Override
    public int hashCode() {
        return authorName.hashCode();
    }
}


