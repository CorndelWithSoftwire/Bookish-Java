package org.softwire.training.bookish.models.database;

import com.google.common.hash.Hashing;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.core.statement.UnableToCreateStatementException;

import org.softwire.training.bookish.models.dao.UserDao;


import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class User {
	String username;
	String passhash;
	String email;
	String phoneNumber;


	public void setPasshashFromString(String password) {
		this.passhash = Hashing.sha256()
				.hashString(password, StandardCharsets.UTF_8)
				.toString();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasshash() {
		return passhash;
	}


	public void setPasshash(String passhash) {
		this.passhash = passhash;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phone) {
		this.phoneNumber = phone;

	}

	public User(String username, String passhash, String email, String phone) {
		this.username = username;
		this.email = email;
		this.phoneNumber = phone;
		setPasshashFromString(passhash);
	}

	public User() {}

	public void getUserFromDatabase(Jdbi jdbi, String username) throws IndexOutOfBoundsException {
		List<User> temp = jdbi.withExtension(UserDao.class, dao -> dao.getUser(username));
		this.email = temp.get(0).email;
		this.phoneNumber = temp.get(0).phoneNumber;
		this.username = temp.get(0).username;
		this.passhash = temp.get(0).passhash;
	}

	public void insertUserToDatabase(Jdbi jdbi){
		User user = jdbi.withExtension(UserDao.class, dao -> {
			dao.insertUser(this.username, this.passhash, this.email, this.phoneNumber);
			return null;
		});

	}



	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", passhash='" + passhash + '\'' +
				", email='" + email + '\'' +
				", phone='" + phoneNumber + '\'' +
				'}';
	}
}

