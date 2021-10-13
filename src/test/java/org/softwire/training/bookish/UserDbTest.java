package org.softwire.training.bookish;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.jupiter.api.Test;
import org.softwire.training.bookish.execeptions.NoUserExeception;
import org.softwire.training.bookish.models.database.User;

import java.util.Date;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class UserDbTest {
	@Test
	void testInsert() {
		Properties connProperties = new Properties();
		connProperties.put("user", "root");
		connProperties.put("password", "c7f/SGXS<80D1H/Iqf0PQp90@dicw(J?");
		connProperties.setProperty("useSSL", "false");
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/bookish", connProperties);
		jdbi.installPlugin(new SqlObjectPlugin());

		Date date = new Date();

		String username = "test" + date.getTime();
		String testPassword = "testPassword";
		String email = "test@example.com";
		String phone = "07756545321";

		User testUser = new User();

		testUser.setUsername(username);
		testUser.setPasshashFromString(testPassword);
		testUser.setEmail(email);
		testUser.setPhoneNumber(phone);

		testUser.insertUserToDatabase(jdbi);

		User assertionUser = new User();
		try {
			assertionUser.getUserFromDatabase(jdbi, username);
		} catch (NoUserExeception e) {
			e.printStackTrace();
		}

		assertThat(assertionUser.getUsername()).isEqualTo(username);
		assertThat(assertionUser.getEmail()).isEqualTo(email);
		assertThat(assertionUser.getPhoneNumber()).isEqualTo(phone);
	}

	@Test
	void getNonExistentUser() {
		Properties connProperties = new Properties();
		connProperties.put("user", "root");
		connProperties.put("password", "c7f/SGXS<80D1H/Iqf0PQp90@dicw(J?");
		connProperties.setProperty("useSSL", "false");
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/bookish", connProperties);
		jdbi.installPlugin(new SqlObjectPlugin());

		String username = "idontexist";

		User user = new User();
		assertThatThrownBy(() -> user.getUserFromDatabase(jdbi, username)).isInstanceOf(IndexOutOfBoundsException.class).hasMessage("Index 0 out of bounds for length 0");
	}
}
