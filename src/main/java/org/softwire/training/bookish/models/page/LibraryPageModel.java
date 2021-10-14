package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.Book;

import java.util.List;

public class LibraryPageModel {
    private List<Book> Books;
    public List<Book> getBooks() { return Books; }
    private int authorId;

    public void setBooks(List<Book> books) { Books = books; }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
