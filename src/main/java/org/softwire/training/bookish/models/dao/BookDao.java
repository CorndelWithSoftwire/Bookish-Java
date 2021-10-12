package org.softwire.training.bookish.models.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.User;

import java.util.List;

public interface BookDao {

    @SqlUpdate("INSERT INTO Book VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
    void insertBook(Integer BookID
            , String Title
            , Integer Category
            , String Created_at
            , String Updated_at
            , String Slug
            , String ISBN
            , String Subtitle
            , String Subjects
            , String Cover_photo_url);

    default void deconstructBook() {

    }

    @SqlQuery("SELECT * FROM Book WHERE Title=:title")
    @RegisterBeanMapper(Book.class)
    List<User> getBookByTitle(@Bind("title") String title);

    @SqlQuery("SELECT * FROM Book")
    @RegisterBeanMapper(Book.class)
    List<User> getBooks();

}
