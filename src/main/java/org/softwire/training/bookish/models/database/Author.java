package org.softwire.training.bookish.models.database;

import java.util.ArrayList;
import java.util.List;

public class Author {
    private int id;
    private String name;
    private String placeOfBirth;
    private List<Book> writtenBookList;

    public Author(){
        id = 0;
        name= "";
        placeOfBirth = "";
        writtenBookList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public void addAuthorsBook(Book book) {writtenBookList.add(book);}

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", writtenBookList=" + writtenBookList +
                '}';
    }
}
