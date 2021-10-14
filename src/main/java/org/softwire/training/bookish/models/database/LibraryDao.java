package org.softwire.training.bookish.models.database;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface LibraryDao {
    @SqlQuery("SELECT * FROM book ORDER BY <column> ASC")
    @RegisterBeanMapper(value = Book.class)
    List<Book> sort(@Define("column") String column);

    @SqlQuery("SELECT * FROM book ORDER BY <column> DESC")
    @RegisterBeanMapper(value = Book.class)
    List<Book> sortReverse(@Define("column") String column);

    @SqlQuery("SELECT * FROM book WHERE author_id = :id")
    @RegisterBeanMapper(value = Book.class)
    List<Book> filterId(@Bind("id") int id);
}
