package org.softwire.training.bookish.models.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.BookDict;

import java.util.Date;
import java.util.List;

public interface BookDao {

    @SqlUpdate("INSERT INTO Book(Title,CreatedAt,UpdatedAt,Slug,Isbn,Subtitle,Subjects,CoverPhotoUrl ) VALUES ( ?, ?, ?, ?, ?, ?, ?,?)")
    @GetGeneratedKeys
    int insertBook(
            String Title,
            Date Created_at,
            Date Updated_at,
            String Slug,
            String ISBN,
            String Subtitle,
            String Subjects,
            String Cover_photo_url
    );

    default void deconstructBook() {

    }

    @SqlUpdate("INSERT INTO Book(Title,CreatedAt,UpdatedAt,Slug,Isbn,Subtitle,Subjects,CoverPhotoUrl ) VALUES ( ?, ?, ?, ?, ?, ?, ?,?);")
    @GetGeneratedKeys
    long insertBookRetrieveId(
            String Title,
            Date Created_at,
            Date Updated_at,
            String Slug,
            String ISBN,
            String Subtitle,
            String Subjects,
            String Cover_photo_url
    );

    @SqlQuery("SELECT * FROM Book WHERE Title=:title")
    @RegisterBeanMapper(Book.class)
    List<Book> getBookByTitle(@Bind("title") String title);

    @SqlQuery("SELECT * FROM Book")
    @RegisterBeanMapper(Book.class)
    List<Book> getBooks();

    @SqlQuery("SELECT Title,BookId FROM BOOK LIMIT :limit offset :page")
    @RegisterBeanMapper(BookDict.class)
    List<BookDict> getAllBooks(@Bind("limit") int limit, @Bind("page") int page);


    @SqlQuery("SELECT BookId,Title,AuthorName,CreatedAt,UpdatedAt,Slug,Isbn,Subtitle,Subjects,CoverPhotoUrl FROM Book JOIN BookAuthor ON Book.BookId = BookAuthor.Book JOIN Authors ON Authors.AuthorId = BookAuthor.Author WHERE Book.BookId = :bookIdNum;")
    @RegisterBeanMapper(Book.class)
    Book getBooksById(@Bind("bookIdNum") Integer bookIdNum);


    @SqlUpdate("DELETE FROM Book WHERE BookId = :bookId")
    @RegisterBeanMapper(Book.class)
    void deleteBook(@Bind("bookId") int bookId);
}
