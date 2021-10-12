package org.softwire.training.bookish;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.jupiter.api.Test;
import org.softwire.training.bookish.models.database.Librarian;
import org.softwire.training.bookish.models.database.User;

import java.util.Date;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LibrarianDbTest {
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

		Librarian testLibrarian = new Librarian();
		testLibrarian.setUsername(username);
		testLibrarian.insertLibrarianIntoDb(jdbi);

		Librarian assertionLibrarian = new Librarian();
		assertionLibrarian.getLibrarianfromDatabase(jdbi, username);
		assertThat(assertionLibrarian.getUsername()).isEqualTo(username);
	}

	@Test
	void testNoLibrarian() {
		Properties connProperties = new Properties();
		connProperties.put("user", "root");
		connProperties.put("password", "c7f/SGXS<80D1H/Iqf0PQp90@dicw(J?");
		connProperties.setProperty("useSSL", "false");
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/bookish", connProperties);
		jdbi.installPlugin(new SqlObjectPlugin());

		String username = "not_a_username";

		Librarian assertionLibrarian = new Librarian();
		assertThatThrownBy(() -> assertionLibrarian.getLibrarianfromDatabase(jdbi, username)).isInstanceOf(IndexOutOfBoundsException.class).hasMessage("Index 0 out of bounds for length 0");
	}
}
