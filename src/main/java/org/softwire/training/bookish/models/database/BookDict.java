package org.softwire.training.bookish.models.database;

public class BookDict {
    String Title;
    int bookId;

    public BookDict(String title, int bookId) {
        Title = title;
        this.bookId = bookId;
    }

    public BookDict() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
