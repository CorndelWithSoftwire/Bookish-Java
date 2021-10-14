package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.services.DatabaseService;

import java.util.List;

public class LibraryPageModel {
    private List<Book> Books;
    public List<Book> getBooks() { return Books; }

    public void setBooks(List<Book> books) { Books = books; }

}
