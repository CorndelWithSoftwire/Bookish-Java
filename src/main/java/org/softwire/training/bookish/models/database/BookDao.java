package org.softwire.training.bookish.models.database;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;

import java.util.List;

public interface BookDao {
    @SqlQuery(" SELECT author.name AS aname, author.id AS aid, author.place_of_birth as aplace_of_birth, " +
            " book.id, book.title, book.ISBN, book.published_date, book.publisher, book.genre, book.number_of_copies, book.author_id " +
            " FROM book JOIN author ON book.author_id = author.id where book.title LIKE :booktitle")
    @RegisterBeanMapper(value = Author.class, prefix = "a")
    @RegisterBeanMapper(value = Book.class)
    @UseRowReducer(BookAuthorReducer.class)
    List<Author> listAuthorAndBooks(@Bind("booktitle") String title);

//    @SqlQuery("SELECT * FROM book WHERE id = :id")
//    @RegisterBeanMapper(value = Book.class)
//    Book filterId(@Bind("id") int id);

    @SqlQuery("SELECT book.id, book.title, book.ISBN, book.published_date, book.publisher, book.genre, book.number_of_copies, book.author_id, " +
            "author.name AS aname, author.id AS aid FROM book JOIN author ON book.author_id = author.id WHERE book.id = :id")
    @RegisterBeanMapper(value = Book.class)
    @RegisterBeanMapper(value = Author.class, prefix = "a")
    @UseRowReducer(BookAndAuthorReducer.class)
    Book filterId(@Bind("id") int id);
}
