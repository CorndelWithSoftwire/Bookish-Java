package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.Author;
import org.softwire.training.bookish.models.database.Book;

import java.util.List;

public class SearchPageModel {

    private List<Author> searchedBook;
    public List<Author> getSearchedBooks() { return searchedBook; }
    public void setBooks(List<Author> searchedBook) { this.searchedBook = searchedBook; }


}
