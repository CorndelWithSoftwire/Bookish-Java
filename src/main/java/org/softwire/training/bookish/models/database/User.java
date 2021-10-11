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

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", passhash='" + passhash + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				'}';
	}
}

