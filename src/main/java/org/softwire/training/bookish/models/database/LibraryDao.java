package org.softwire.training.bookish.models.database;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface LibraryDao {
    @SqlQuery("SELECT book.id, book.title, book.ISBN, book.published_date, book.publisher, book.genre, book.number_of_copies, book.author_id, book.book_cover_url, " +
            "author.name AS aname, author.id AS aid FROM book JOIN author ON book.author_id = author.id")
    @RegisterBeanMapper(value = Book.class)
    @RegisterBeanMapper(value = Author.class, prefix = "a")
    @UseRowReducer(BookAndAuthorReducer.class)
    List<Book> listOfBooks();

    @SqlQuery("SELECT book.id, book.title, book.ISBN, book.published_date, book.publisher, book.genre, book.number_of_copies, book.author_id, " +
            "author.name AS aname, author.id AS aid FROM book JOIN author ON book.author_id = author.id ORDER BY <column> ASC")
    @RegisterBeanMapper(value = Book.class)
    @RegisterBeanMapper(value = Author.class, prefix = "a")
    @UseRowReducer(BookAndAuthorReducer.class)
    List<Book> sort(@Define("column") String column);

    @SqlQuery("SELECT book.id, book.title, book.ISBN, book.published_date, book.publisher, book.genre, book.number_of_copies, book.author_id, " +
            "author.name AS aname, author.id AS aid FROM book JOIN author ON book.author_id = author.id ORDER BY <column> DESC")
    @RegisterBeanMapper(value = Book.class)
    @RegisterBeanMapper(value = Author.class, prefix = "a")
    @UseRowReducer(BookAndAuthorReducer.class)
    List<Book> sortReverse(@Define("column") String column);

    @SqlQuery("SELECT * FROM book WHERE author_id = :id")
    @RegisterBeanMapper(value = Book.class)
    List<Book> filterId(@Bind("id") int id);


    @SqlQuery("SELECT book.id, book.title, book.ISBN, book.published_date, book.publisher, book.genre, book.number_of_copies, book.author_id, " +
            "author.name AS aname, author.id AS aid FROM book JOIN author ON book.author_id = author.id WHERE book.author_id = :id ORDER BY <column>")
    @RegisterBeanMapper(value = Book.class)
    @RegisterBeanMapper(value = Author.class, prefix = "a")
    @UseRowReducer(BookAndAuthorReducer.class)
    List<Book> filterAndSort(@Define("column") String column, @Bind("id") int id);

    @SqlQuery("SELECT book.id, book.title, book.ISBN, book.published_date, book.publisher, book.genre, book.number_of_copies, book.author_id, " +
            "author.name AS aname, author.id AS aid FROM book JOIN author ON book.author_id = author.id WHERE book.author_id = :id ORDER BY <column> DESC")
    @RegisterBeanMapper(value = Book.class)
    @RegisterBeanMapper(value = Author.class, prefix = "a")
    @UseRowReducer(BookAndAuthorReducer.class)
    List<Book> filterAndSortReverse(@Define("column") String column, @Bind("id") int id);

}
