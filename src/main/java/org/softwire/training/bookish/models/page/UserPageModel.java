package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.User;

import java.util.List;

public class UserPageModel {
    private List<Book> Books;
    private List<Book> availableBooksToBorrow;
    private User user;

    public List<Book> getBooks() {
        return Books;
    }

    public void setBooks(List<Book> books) {
        Books = books;
    }

    public User getUser() {
        return user;
    }

    public List<Book> getAvailableBooksToBorrow() {
        return availableBooksToBorrow;
    }

    public void setAvailableBooksToBorrow(List<Book> availableBooksToBorrow) {
        this.availableBooksToBorrow = availableBooksToBorrow;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
