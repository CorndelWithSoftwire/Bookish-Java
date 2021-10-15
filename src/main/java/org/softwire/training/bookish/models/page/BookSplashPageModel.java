package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.Author;
import org.softwire.training.bookish.models.database.Book;

public class BookSplashPageModel {
    private Book Book;
    private Author Author;
    public Book getBook() { return Book; }
    public Author getAuthor() { return Author; }
    public void setBook(Book book) { Book = book; }
    public void setAuthor(Author author) {Author = author;}
}
