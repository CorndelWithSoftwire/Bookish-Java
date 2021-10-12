package org.softwire.training.bookish.services;

import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.softwire.training.bookish.models.database.User;
import org.softwire.training.bookish.models.database.UserDao;
import org.springframework.stereotype.Service;
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

    public List<User> sort(String column) {
        jdbi.installPlugin( new SqlObjectPlugin() );
        List<User> userList = jdbi.withExtension(
                UserDao.class, dao -> {
                    return dao.sort(column);

                });

        return userList;
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
