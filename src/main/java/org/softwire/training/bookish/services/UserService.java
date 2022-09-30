package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends DatabaseService {

    public static List<User> getAllUsers() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM Users")
                        .map((rs, ctx) -> new User(rs.getInt("ID"), rs.getString("Name"),
                                rs.getString("Password"), rs.getString("Type"), rs.getInt("Active"))).list());
    }

    public List<User> getAllLibrarians(){
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM Users WHERE Type = 'Librarian'")
                        .mapToBean(User.class)
                        .list()
        );
    }

    public void addUser(String name, String password, String type, int active) {
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO Users (name, password, type, active) VALUES (:name, :password, :type, :active)")
                        .bind("name", name).bind("password", password)
                        .bind("type", type).bind("active", active)
                        .execute()
        );
    }

    public void deleteUser(int userID) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM Users WHERE id = :id")
                        .bind("id", userID)
                        .execute()
        );
    }

    public static User getActiveUser(){
        for (User user: getAllUsers()){
            if (user.getActive() == 1){
                return user;
            }
        }
        return null;
    }

    public static User setUserActive(User user){
        User previousActive =  getActiveUser();
        if (previousActive != null){
        previousActive.setActive(0);
        jdbi.useHandle(handle -> handle.createUpdate("UPDATE Users SET Active = 0 WHERE ID = "+previousActive.getId()).execute());}
        user.setActive(1);
        jdbi.useHandle(handle -> handle.createUpdate("UPDATE Users SET Active = 1 WHERE ID = "+user.getId()).execute());
        //UserPageModel.setActiveUser(user);
        return user;
    }
}
