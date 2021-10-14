package org.softwire.training.bookish.models.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.BookDict;
import org.springframework.data.util.Pair;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BookDao {

    @SqlUpdate("INSERT INTO Book VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)")
    void insertBook(Integer BookID
            , String Title
            , Date Created_at
            , Date Updated_at
            , String Slug
            , String ISBN
            , String Subtitle
            , String Subjects
            , String Cover_photo_url);

    default void deconstructBook() {

    }

    @SqlQuery("SELECT * FROM Book WHERE Title=:title")
    @RegisterBeanMapper(Book.class)
    List<Book> getBookByTitle(@Bind("title") String title);

    @SqlQuery("SELECT * FROM Book")
    @RegisterBeanMapper(Book.class)
    List<Book> getBooks();

    @SqlQuery("SELECT Title,BookId FROM BOOK LIMIT :limit offset :page")
    @RegisterBeanMapper(BookDict.class)
    List<BookDict> getAllBooks(@Bind("limit") int limit, @Bind("page") int page);
}
