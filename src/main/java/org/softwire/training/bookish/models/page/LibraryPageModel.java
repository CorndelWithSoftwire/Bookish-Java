package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.Author;
import org.softwire.training.bookish.models.database.Book;

import java.util.List;

public class LibraryPageModel {
    private List<Book> Books;
    public List<Book> getBooks() { return Books; }
    private int authorId;
    private String authorName;
    private String searchString;

    private Author Author;
    private Book Book;
    public Book getBook() { return Book; }

    public void setBook(Book book) { Book = book; }

    public Author getAuthor() { return Author; }

    public void setAuthor(Author author) {Author = author;}

    public void setBooks(List<Book> books) { Books = books; }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
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
}
