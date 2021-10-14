package org.softwire.training.bookish.services;

import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.BookDao;
import org.springframework.stereotype.Service;

@Service
public class BookSplashService extends DatabaseService {

    public Book getBook(int id) {
        jdbi.installPlugin(new SqlObjectPlugin()); // usually when connecting

        return jdbi.withExtension(
                BookDao.class, dao -> dao.filterId(id));
    }

    public void editBook(Book book) {
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO book (id, title, ISBN, published_date, publisher, genre, number_of_copies, author_id)\n" +
                                "VALUES (4, 'Harry Potter and the Goblet of Your Mum', '1111111151', '1997', 'Scholastic', 'fantasy', 2, 1)\n" +
                                "ON DUPLICATE KEY UPDATE id=VALUES(id),\n" +
                                "title=VALUES(title),\n" +
                                "ISBN=VALUES(ISBN),\n" +
                                "published_date=VALUES(published_date),\n" +
                                "publisher=VALUES(publisher),\n" +
                                "genre=VALUES(genre),\n" +
                                "number_of_copies=VALUES(number_of_copies),\n" +
                                "author_id=VALUES(author_id);")
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
}
