package org.softwire.training.bookish.services;

import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.LibraryDao;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibraryService extends DatabaseService {

    public List<Book> getAllBooks() {
        jdbi.installPlugin(new SqlObjectPlugin()); // usually when connecting

        return jdbi.withExtension(
                LibraryDao.class, LibraryDao::listOfBooks);
    }

    public void addBook(Book book) {
            jdbi.useHandle(handle ->
                    handle.createUpdate("INSERT INTO book (title, ISBN, published_date, publisher, genre, number_of_copies, author_id) " +
                                    "VALUES (:title, :ISBN, :published_date, :publisher, :genre, :number_of_copies, :author_id)")
                            .bind("title", book.getTitle())
                            .bind("ISBN", book.getIsbn())
                            .bind("published_date", book.getPublishedDate())
                            .bind("publisher", book.getPublisher())
                            .bind("genre", book.getGenre())
                            .bind("number_of_copies", book.getNumberOfCopies())
                            .bind("author_id", book.getAuthorId())
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

    public List<Book> sort(String column) {
        jdbi.installPlugin( new SqlObjectPlugin() ); // usually when connecting

        return jdbi.withExtension(
                LibraryDao.class, dao -> dao.sort(column));
    }

    public List<Book> filterAndSort(String column, int id) {
        jdbi.installPlugin( new SqlObjectPlugin() ); // usually when connecting

        return jdbi.withExtension(
                LibraryDao.class, dao -> dao.filterAndSort(column, id));
    }

    public List<Book> filterAndSortReverse(String column, int id) {
        jdbi.installPlugin( new SqlObjectPlugin() ); // usually when connecting

        return jdbi.withExtension(
                LibraryDao.class, dao -> dao.filterAndSortReverse(column, id));
    }

    public List<Book> sortReverse(String column) {
        jdbi.installPlugin( new SqlObjectPlugin() );

        return jdbi.withExtension(
                LibraryDao.class, dao -> dao.sortReverse(column));
    }

    public List<Book> filterId(int id){
        jdbi.installPlugin( new SqlObjectPlugin() );

        return jdbi.withExtension(
                LibraryDao.class, dao -> dao.filterId(id));
    }
}
