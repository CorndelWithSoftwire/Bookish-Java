package org.softwire.training.bookish.models.database;

import com.google.common.hash.Hashing;
import org.jdbi.v3.core.Jdbi;

import org.softwire.training.bookish.execeptions.NoUserExeception;
import org.softwire.training.bookish.models.dao.UserDao;


import java.nio.charset.StandardCharsets;
import java.util.List;

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

	public void getUserFromDatabase(Jdbi jdbi, String username) throws NoUserExeception {
		List<User> users = jdbi.withExtension(UserDao.class, dao -> dao.getUser(username));
		try {
			User tempUser = users.get(0);
			this.username = tempUser.getUsername();
			this.email = tempUser.getEmail();
			this.passhash = tempUser.getPasshash();
			this.phoneNumber = tempUser.getPhoneNumber();
		} catch (Exception e) {
			throw new NoUserExeception("No user in the database under that username");
		}
	}

	public void insertUserToDatabase(Jdbi jdbi){
		jdbi.useExtension(UserDao.class, dao -> dao.insertUser(this.username, this.passhash, this.email, this.phoneNumber));

	}

	public void deleteUserFromDatabase(Jdbi jdbi) {
		jdbi.useExtension(UserDao.class, dao -> dao.deleteUser(this.username));
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

