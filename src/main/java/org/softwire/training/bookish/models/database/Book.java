package org.softwire.training.bookish.models.database;

import java.util.Date;

public class Book {
    private int iD;
    private String title;
    private int iSBN;
    private String publishedDate;
    private String publisher;
    private String genre;
    private int numOfCopies;
    private int authorID;

//    public Book(int iD, String title, String iSBN, Date publishedDate, String publisher, String genre, String summary, String numOfCopies, String authorID) {
//        this.iD = iD;
//        this.title = title;
//        this.iSBN = iSBN;
//        this.publishedDate = publishedDate;
//        this.publisher = publisher;
//        this.genre = genre;
//        this.numOfCopies = numOfCopies;
//        this.authorID = authorID;
//    }


    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getiSBN() {
        return iSBN;
    }

    public void setiSBN(int iSBN) {
        this.iSBN = iSBN;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getNumOfCopies() {
        return numOfCopies;
    }

    public void setNumOfCopies(int numOfCopies) {
        this.numOfCopies = numOfCopies;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }
}