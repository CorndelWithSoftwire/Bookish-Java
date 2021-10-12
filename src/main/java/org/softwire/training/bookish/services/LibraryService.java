package org.softwire.training.bookish.services;

import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.LibraryDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService extends DatabaseService {
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

    public void deleteBook(int bookID) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM book WHERE id = :id")
                        .bind("id", bookID)
                        .execute()
        );
    }

//    public List<Book> sort(String column) {
//        List<Book> bookList = jdbi.withHandle(handle ->
//                handle.createQuery("SELECT * FROM book ORDER BY :column_name")
//                        .bind("column_name", column)
//                        .mapToBean(Book.class)
//                        .list()
//        );
//        return bookList;
//    }

    public List<Book> sort(String column) {
        jdbi.installPlugin( new SqlObjectPlugin() ); // usually when connecting
        List<Book> bookList = jdbi.withExtension(
                LibraryDao.class, dao -> {
                    return dao.sort(column);
                });

        return bookList;
    }
}
