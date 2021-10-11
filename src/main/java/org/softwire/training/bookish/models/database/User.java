package org.softwire.training.bookish.models.database;

import com.google.common.hash.Hashing;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.core.statement.UnableToCreateStatementException;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class User {
	String username;
	String passhash;
	String email;
	String phone;

	public void insertIntoDB(Jdbi jdbi) {
		jdbi.withHandle(handle -> {
			try {
				return handle.execute("insert into Users values (?, ?, ?, ?)", this.getUsername(), this.getPasshash(), this.getEmail(), this.getPhone());
			} catch (UnableToCreateStatementException e) {
				return null;
			}
		});
	}

	public List<User> getUserByUsername(Jdbi jdbi, String username) {
		return jdbi.withHandle(handle -> handle
				.createQuery("select * from Users where Username=:username")
				.bind("username", username)
				.mapToBean(User.class)
				.list());
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User(String username, String passhash, String email, String phone) {
		this.username = username;
		this.passhash = passhash;
		this.email = email;
		this.phone = phone;
	}

	public User() {}
}

