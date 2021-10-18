package org.softwire.training.bookish.models.database;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface AuthorDao {
    @SqlQuery("SELECT * FROM author ORDER BY <column> ASC")
    @RegisterBeanMapper(value = Author.class)
    List<Author> sort(@Define("column") String column);

    @SqlQuery("SELECT * FROM author ORDER BY <column> DESC")
    @RegisterBeanMapper(value = Author.class)
    List<Author> sortReverse(@Define("column") String column);

    @SqlQuery("SELECT * FROM author WHERE id = :id")
    @RegisterBeanMapper(value = Author.class)
    Author findAuthor(@Bind("id") int id);


}
