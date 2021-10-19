package org.softwire.training.bookish.services;

import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.BookDao;
import org.springframework.stereotype.Service;
import org.softwire.training.bookish.models.database.Author;
import org.softwire.training.bookish.models.database.AuthorDao;

@Service
public class BookSplashService extends DatabaseService {

    public Book getBook(int id) {
        jdbi.installPlugin(new SqlObjectPlugin());

        return jdbi.withExtension(
                BookDao.class, dao -> dao.filterId(id));
    }

    public Author getAuthor(int id) {
        jdbi.installPlugin(new SqlObjectPlugin());

        return jdbi.withExtension(
                AuthorDao.class, dao -> dao.findAuthor(id));
    }

    public void editBook(Book book) {
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO book (id, title, ISBN, published_date, publisher, genre, number_of_copies, author_id) " +
                                "VALUES (:id, :title, :ISBN, :published_date, :publisher, :genre, :number_of_copies, :author_id) " +
                                "ON DUPLICATE KEY UPDATE id=VALUES(id), " +
                                "title=VALUES(title), " +
                                "ISBN=VALUES(ISBN), " +
                                "published_date=VALUES(published_date), " +
                                "publisher=VALUES(publisher), " +
                                "genre=VALUES(genre), " +
                                "number_of_copies=VALUES(number_of_copies), " +
                                "author_id=VALUES(author_id);")
                        .bind("id", book.getId())
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
