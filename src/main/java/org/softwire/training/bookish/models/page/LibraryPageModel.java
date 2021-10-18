package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.Book;

import java.util.List;

public class LibraryPageModel {
    private List<Book> Books;
    public List<Book> getBooks() { return Books; }
    private int authorId;
    private String searchString;

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
}
