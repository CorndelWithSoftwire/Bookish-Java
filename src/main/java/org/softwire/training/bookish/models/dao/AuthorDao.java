package org.softwire.training.bookish.models.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.softwire.training.bookish.models.database.Author;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.User;

import java.util.List;

public interface AuthorDao {

    @SqlUpdate("INSERT INTO Authors VALUES (?, ?)")
    void insertAuthors(Integer authorID
            , String authorName
            );

    @SqlQuery("SELECT * FROM Authors WHERE AuthorName=:authorName")
    @RegisterBeanMapper(Author.class)
    List<Author> getAuthorByName(@Bind("authorName") String authorName);

    @SqlQuery("SELECT * FROM Authors")
    @RegisterBeanMapper(Author.class)
    List<User> getAuthors();
}
