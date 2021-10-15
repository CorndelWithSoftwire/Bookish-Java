package org.softwire.training.bookish;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.BookDict;
import org.softwire.training.bookish.models.database.Books;
import org.softwire.training.bookish.populateDB.PopulateDB;

import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BookDbTest {
    Jdbi _jdbi;


    @BeforeAll
    static void setUpDb() {
        Properties connProperties = new Properties();
        connProperties.put("user", "root");
        connProperties.put("password", "c7f/SGXS<80D1H/Iqf0PQp90@dicw(J?");
        connProperties.setProperty("useSSL", "false");
        Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/bookish_mock", connProperties);
        jdbi.installPlugin(new SqlObjectPlugin());
    }


    @Test
    void testBookInsert() {
        Properties connProperties = new Properties();
        connProperties.put("user", "root");
        connProperties.put("password", "c7f/SGXS<80D1H/Iqf0PQp90@dicw(J?");
        connProperties.setProperty("useSSL", "false");
        Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/bookish", connProperties);
        jdbi.installPlugin(new SqlObjectPlugin());

        Book book = new Book();
        book.setBookID(567);
        book.setAuthors("Someone...");
    }

    @Test
    void testGetBooks() {
        // test if we  get 50 BookDict since it's limited by 50 in the sql query.
        // BookDict consists of a name and an ID
        int page = 1;
        List<BookDict> books = new Books().getBooksList(_jdbi, page);
        assertThat(books.size()).isEqualTo(50);
    }

}
