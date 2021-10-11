package org.softwire.training.bookish.models.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.softwire.training.bookish.models.database.User;

import java.util.List;

public interface UserDao {
	@SqlUpdate("INSERT INTO Users VALUES (?, ?, ?, ?)")
	void insertUser(String username, String passhash, String email, String phoneNumber);



	@SqlQuery("SELECT * FROM Users WHERE Username=:username")
	@RegisterBeanMapper(User.class)
	List<User> getUser(@Bind("username") String name);

	@SqlQuery("SELECT * FROM Users")
	@RegisterBeanMapper(User.class)
	List<User> getUsers();
}
