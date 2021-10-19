package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Author;

public class BookSplashPageModel {
    private Author Author;
    private Book Book;
    public Book getBook() { return Book; }

    public void setBook(Book book) { Book = book; }

    public Author getAuthor() { return Author; }
    public void setAuthor(Author author) {Author = author;}
}
