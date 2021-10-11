package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService extends DatabaseService{
    public List<Book> getAllBooks() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM book")
                        .mapToBean(Book.class)
                        .list()
        );
    }

    public void addBook(Book book) {
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO book (title, ISBN, published_date, publisher, genre, number_of_copies, author_id) " +
                                "VALUES (:title, :ISBN, :published_date, :publisher, :genre, :number_of_copies, :author_id)")
                        .bind("title", book.getTitle())
                        .bind("ISBN", book.getiSBN())
                        .bind("published_date", book.getPublishedDate())
                        .bind("publisher", book.getPublisher())
                        .bind("genre", book.getGenre())
                        .bind("number_of_copies", book.getNumOfCopies())
                        .bind("author_id", book.getAuthorID())
                        .execute()
        );
    }

    public List<Book> sortByTitle() {
        List<Book> bookList = jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM book ORDER BY title")
                        .mapToBean(Book.class)
                        .list()
        );
        return bookList;
    }
    public List<Book> sortByISBN() {
        List<Book> bookList = jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM book ORDER BY isbn")
                        .mapToBean(Book.class)
                        .list()
        );
        return bookList;
    }

    public List<Book> sortByPublishedDate() {
        List<Book> bookList = jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM book ORDER BY published_date")
                        .mapToBean(Book.class)
                        .list()
        );
        return bookList;
    }

    public List<Book> sortByPublisher() {
        List<Book> bookList = jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM book ORDER BY publisher")
                        .mapToBean(Book.class)
                        .list()
        );
        return bookList;
    }

    public List<Book> sortByGenre() {
        List<Book> bookList = jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM book ORDER BY genre")
                        .mapToBean(Book.class)
                        .list()
        );
        return bookList;
    }

    public List<Book> sortByNumberOfCopies() {
        List<Book> bookList = jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM book ORDER BY number_of_copies")
                        .mapToBean(Book.class)
                        .list()
        );
        return bookList;
    }

    public List<Book> sortByAuthorID() {
        List<Book> bookList = jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM book ORDER BY author_id")
                        .mapToBean(Book.class)
                        .list()
        );
        return bookList;
    }

}
