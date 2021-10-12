package org.softwire.training.bookish.models.database;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface LibraryDao {
    @SqlQuery("SELECT * FROM book ORDER BY <column>")
    @RegisterBeanMapper(value = Book.class)
    public List<Book> sort(@Define("column") String column);

    @SqlUpdate("INSERT INTO book (title, ISBN, published_date, publisher, genre, number_of_copies, author_id) " +
            "VALUES (:title, :ISBN, :published_date, :publisher, :genre, :number_of_copies, :author_id)")
    void insertBook(@Bind("title") String title, @Bind("ISBN") int ISBN, @Bind("published_date") String published_date,
                    @Bind("publisher") String pulisher, @Bind("genre") String genre, @Bind("number_of_copies") int number_of_copies,
                    @Bind("author_id") int author_id);

}
