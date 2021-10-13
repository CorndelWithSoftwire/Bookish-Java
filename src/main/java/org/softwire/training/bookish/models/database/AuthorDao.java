package org.softwire.training.bookish.models.database;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;

import java.util.List;

public interface AuthorDao {
    @SqlQuery("SELECT * FROM author ORDER BY <column> ASC")
    @RegisterBeanMapper(value = Author.class)
    List<Author> sort(@Define("column") String column);

    @SqlQuery("SELECT * FROM author ORDER BY <column> DESC")
    @RegisterBeanMapper(value = Author.class)
    List<Author> sortReverse(@Define("column") String column);

    @SqlQuery(" SELECT author.name AS aname, author.id AS aid, author.place_of_birth as aplace_of_birth, " +
            " book.id, book.title, book.ISBN, book.published_date, book.publisher, book.genre, book.number_of_copies, book.author_id " +
            " FROM book JOIN author ON book.author_id = author.id where author.id LIKE :authorId")
    @RegisterBeanMapper(value = Author.class, prefix = "a")
    @RegisterBeanMapper(value = Book.class)
    @UseRowReducer(BookAuthorReducer.class)
    List<Author> listAuthorAndBooks(@Bind("authorId") String title);
}
