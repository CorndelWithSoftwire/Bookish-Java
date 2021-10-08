package org.softwire.training.bookish.models.database;

import java.util.Date;

public class Book {
    private final int iD;
    private String title;
    private String iSBN;
    private Date publishedDate;
    private String publisher;
    private String genre;
    private String numOfCopies;
    private String authorID;

    public Book(int iD, String title, String iSBN, Date publishedDate, String publisher, String genre, String summary, String numOfCopies, String authorID) {
        this.iD = iD;
        this.title = title;
        this.iSBN = iSBN;
        this.publishedDate = publishedDate;
        this.publisher = publisher;
        this.genre = genre;
        this.numOfCopies = numOfCopies;
        this.authorID = authorID;
    }

    public void setNumOfCopies(String numOfCopies) {
        this.numOfCopies = numOfCopies;
    }

    public int getiD() {
        return iD;
    }

    public String getTitle() {
        return title;
    }

    public String getiSBN() {
        return iSBN;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getGenre() {
        return genre;
    }

    public String getNumOfCopies() {
        return numOfCopies;
    }

    public String getAuthorID() {
        return authorID;
    }
}
