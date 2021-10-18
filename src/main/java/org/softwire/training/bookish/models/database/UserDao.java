package org.softwire.training.bookish.models.database;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;

import java.util.List;

public interface UserDao {
    @SqlQuery("SELECT u.id as uid, u.forename as uforename, u.surname as usurname, " +
            "c.id, c.book_id, c.borrowed_by, c.return_date " +
            "FROM user u, copy_registry c WHERE u.id = c.borrowed_by")
    @RegisterBeanMapper(value = User.class, prefix = "u")
    @RegisterBeanMapper(value = CopyRegistry.class)
    @UseRowReducer(UserCopyRegistryReducer.class)
    List<User> listLoanUsers();

    @SqlQuery("SELECT book.id, book.title, book.isbn, book.published_date, book.publisher, book.genre, book.number_of_copies, book.author_id, " +
            "author.name AS aname, author.id AS aid " +
            "FROM book " +
            "JOIN copy_registry on book.id = copy_registry.book_id " +
            "JOIN user on copy_registry.borrowed_by = user.id " +
            "JOIN author on book.author_id = author.id " +
            "WHERE user.id = :id")
    @RegisterBeanMapper(value = Book.class)
    @RegisterBeanMapper(value = Author.class, prefix = "a")
    @UseRowReducer(BookAndAuthorReducer.class)
    List<Book> booksBorrowedByUserID(@Bind("id") int id);

    @SqlQuery("SELECT book.id, book.title, book.isbn, book.published_date, book.publisher, book.genre, book.number_of_copies, book.author_id, " +
            "author.name AS aname, author.id AS aid " +
            "FROM book " +
            "JOIN author on book.author_id = author.id " +
            "WHERE number_of_copies != 0")
    @RegisterBeanMapper(value = Book.class)
    @RegisterBeanMapper(value = Author.class, prefix = "a")
    @UseRowReducer(BookAndAuthorReducer.class)
    List<Book> availableBooksToBorrow();


    @SqlQuery("SELECT * FROM user ORDER BY <column> ASC")
    @RegisterBeanMapper(value = User.class)
    List<User> sort(@Define("column") String column);

    @SqlQuery("SELECT * FROM user ORDER BY <column> DESC")
    @RegisterBeanMapper(value = User.class)
    List<User> sortReverse(@Define("column") String column);
}
