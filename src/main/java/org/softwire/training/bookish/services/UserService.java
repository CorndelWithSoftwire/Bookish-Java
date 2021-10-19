package org.softwire.training.bookish.services;

import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.User;
import org.softwire.training.bookish.models.database.UserDao;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserService extends DatabaseService {

    public List<User> getAllUsers() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM user")
                        .mapToBean(User.class)
                        .list()
        );
    }

    public void borrow(int book_id, int user_id) {

        // check that book id exists in user's booklist
        HashMap<Integer, Book> bookHashMap = this.getBookHashMap(user_id);
        Book book = bookHashMap.get(book_id);
        // quick break is already in inventory
        if (book != null) {
            return;
        }

        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE book " +
                                "SET number_of_copies = GREATEST(0, number_of_copies - 1) " +
                                "WHERE book.id = :book_id")
                        .bind("book_id", book_id)
                        .execute()
        );

        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO copy_registry (book_id, borrowed_by, return_date) VALUES " +
                                "(:book_id, :user_id, '2021-12-31');")
                        .bind("book_id", book_id)
                        .bind("user_id", user_id)
                        .execute()
        );
    }

    public void returnBook(int book_id, int user_id) {
        // check that book id exists in user's booklist
        HashMap<Integer, Book> bookHashMap = this.getBookHashMap(user_id);
        Book book = bookHashMap.get(book_id);
        // quick break if book id is not in inventory
        if (book == null) {
            return;
        }

        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM copy_registry WHERE book_id = :book_id and borrowed_by = :user_id ;")
                        .bind("book_id", book_id)
                        .bind("user_id", user_id)
                        .execute()
        );

        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE book " +
                                "SET number_of_copies = GREATEST(0, number_of_copies + 1) " +
                                "WHERE id = :book_id;")
                        .bind("book_id", book_id)
                        .execute()
        );
    }



    public List<Book> getBorrowList(int id) {
        jdbi.installPlugin( new SqlObjectPlugin() );

        return jdbi.withExtension(
                UserDao.class, dao -> dao.booksBorrowedByUserID(id));
    }

    public HashMap<Integer, Book> getBookHashMap(int id) {
        List<Book> bookList= this.getBorrowList(id);
        HashMap<Integer, Book> bookHashMap = new HashMap<>();
        for (Book book: bookList) {
            bookHashMap.put(book.getId(), book);
        }
        return bookHashMap;
    }

    public List<Book> getAvailableBooksToBorrow() {
        jdbi.installPlugin( new SqlObjectPlugin() );
        return jdbi.withExtension(
                UserDao.class, UserDao::availableBooksToBorrow);
    }

    public List<User> sort(String column) {
        jdbi.installPlugin( new SqlObjectPlugin() );

        return jdbi.withExtension(
                UserDao.class, dao -> dao.sort(column));
    }

    public List<User> sortReverse(String column) {
        jdbi.installPlugin( new SqlObjectPlugin() );

        return jdbi.withExtension(
                UserDao.class, dao -> dao.sortReverse(column));
    }

    public void addUser(User user) {
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO user (forename, surname) VALUES (:forename, :surname)")
                        .bind("forename", user.getForename())
                        .bind("surname", user.getSurname())
                        .execute()
        );
    }

    public void deleteUser(int userId) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM user WHERE id = :id")
                        .bind("id", userId)
                        .execute()
        );
    }
}
