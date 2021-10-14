package org.softwire.training.bookish.models.database;


import java.util.Objects;

public class Book {
    private int id;
    private String title;
    private int isbn;
    private String publishedDate;
    private String publisher;
    private String genre;
    private int numberOfCopies;
    private int authorId;
    private String authorName;

    public Book(int id, String title, int isbn, String publishedDate, String publisher, String genre, int numberOfCopies, int authorId, String authorName) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publishedDate = publishedDate;
        this.publisher = publisher;
        this.genre = genre;
        this.numberOfCopies = numberOfCopies;
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public Book() {
        this.id = 0;
        this.title = "";
        this.isbn = 0;
        this.publishedDate = "";
        this.publisher = "";
        this.genre = "";
        this.numberOfCopies = 0;
        this.authorId = 0;
        this.authorName = "";
    }

    public int getId() {
        return id;

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
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

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && isbn == book.isbn && numberOfCopies == book.numberOfCopies && authorId == book.authorId && Objects.equals(title, book.title) && Objects.equals(publishedDate, book.publishedDate) && Objects.equals(publisher, book.publisher) && Objects.equals(genre, book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, isbn, publishedDate, publisher, genre, numberOfCopies, authorId);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn=" + isbn +
                ", publishedDate='" + publishedDate + '\'' +
                ", publisher='" + publisher + '\'' +
                ", genre='" + genre + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                ", authorId=" + authorId +
                '}';
    }
}