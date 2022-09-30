package org.softwire.training.bookish.models.database;

public class Book {

    public Book(int id, String title, String author, String ISBN, String genre, int copies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.copies = copies;
    }

    public Book(){}

    private int id;
    private String title;
    private String author;
    private String ISBN;
    private String genre;
    private int copies;


    public int getID(){return this.id;}
    public String getTitle(){return this.title;}
    public String getAuthor(){return this.author;}
    public String getISBN(){return this.ISBN;}
    public String getGenre(){return this.genre;}
    public int getCopies(){return this.copies;}

    public void setId(int id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setAuthor(String author) {this.author = author;}
    public void setISBN(String ISBN) {this.ISBN = ISBN;}
    public void setGenre(String genre) {this.genre = genre;}
    public void setCopies(int copies) {this.copies = copies;}
}
