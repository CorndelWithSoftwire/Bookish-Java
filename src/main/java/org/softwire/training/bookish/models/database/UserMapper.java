package org.softwire.training.bookish.models.database;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
	@Override
	public User map(ResultSet rs, StatementContext ctx) throws SQLException {
		try {
			return new User(rs.getString("Username"), rs.getString("Password_hash"), rs.getString("Email"), rs.getString("Phone_number"));
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
