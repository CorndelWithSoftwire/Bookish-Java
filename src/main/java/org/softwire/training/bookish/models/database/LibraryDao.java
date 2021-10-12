package org.softwire.training.bookish.models.database;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface LibraryDao {
    @SqlQuery("SELECT * FROM book ORDER BY <column>")
    @RegisterBeanMapper(value = Book.class)
    public List<Book> sort(@Define("column") String column);
}
