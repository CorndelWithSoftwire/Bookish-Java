package org.softwire.training.bookish.models.database;

public class Book {
    private int id;
    private String title;
    private String author;
    private String ISBN;
    private String genre;
    private int copies;


    public Book(){
    }

    public int getID(){return this.id;}
    public String getTitle(){return this.title;}
    public String getAuthor(){return this.author;}
    public String getISBN(){return this.ISBN;}
    public String getGenre(){return this.genre;}
    public int getCopies(){return this.copies;}
}
