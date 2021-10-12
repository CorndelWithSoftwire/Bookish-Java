package org.softwire.training.bookish.populateDB;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.softwire.training.bookish.models.dao.AuthorDao;
import org.softwire.training.bookish.models.dao.BookDao;
import org.softwire.training.bookish.models.dao.LibrarianDao;
import org.softwire.training.bookish.models.dao.UserDao;
import org.softwire.training.bookish.models.database.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PopulateDB {
	public static void main(String[] args) throws SQLException {
		Properties connProperties = new Properties();
		connProperties.put("user", "root");
		connProperties.put("password", "c7f/SGXS<80D1H/Iqf0PQp90@dicw(J?");
		connProperties.setProperty("useSSL", "false");
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/bookish", connProperties);
		jdbi.installPlugin(new SqlObjectPlugin());
		jdbi.registerRowMapper(new UserMapper());

		/*
		populateUsers(jdbi);
		makeLibrarians(jdbi);
		*/

		Books allBooks = new Books("resources/books.csv");

//		populateBooks(jdbi, allBooks);

		Authors allAuthors = new Authors(allBooks);

//		populateAuthors(jdbi, allAuthors);

		List<Author> authorObj = jdbi.withExtension(AuthorDao.class, dao -> dao.getAuthorByName("  Andrew Glover"));
		System.out.println(authorObj);


		List<User> users = jdbi.withExtension(UserDao.class, dao -> dao.getUser("Test3"));
		System.out.println(users);

		List<Librarian> librarians = jdbi.withExtension(LibrarianDao.class, dao -> {
			//dao.createLibrarian("Test3");
			return dao.getLibrarian("Test3");
		});
		System.out.println(librarians);
	}

	private static void populateBooks(Jdbi jdbi, Books allBooks) {
		Integer count = jdbi.withExtension(BookDao.class, dao -> {
			for (Book each : allBooks.booksList) {
				dao.insertBook(
						each.getBookID(),
						each.getTitle(),
						each.getCategory(),
						each.getCreated_at(),
						each.getUpdated_at(),
						each.getSlug(),
						each.getISBN(),
						each.getSubtitle(),
						each.getSubjects(),
						each.getCover_photo_url()
				);

			};
			return 5;
		});
	}

	private static void populateAuthors(Jdbi jdbi, Authors allAuthors) {
		Integer count = jdbi.withExtension(AuthorDao.class, dao -> {
			for (Author each : allAuthors.getAuthors()) {
				dao.insertAuthors(
						each.getAuthorId(),
						each.getAuthorName()
				);
			};
			return 5;
		});
	}

	private static void makeLibrarians(Jdbi jdbi) throws SQLException {
		List<String> librarians = Arrays.asList("Sears", "Kent", "Merrill");
		for (String librarian: librarians) {
			jdbi.withHandle(handle -> {
				return handle.execute("insert into Librarians values (?)", librarian);
			});
		}
	}

	/*
	private static void populateUsers(Jdbi jdbi) throws SQLException {
		ArrayList<User> users = getUsersFromCsv();
		for (User user : users){
			user.insertIntoDB(jdbi);
		}
	}

	 */

	private static ArrayList<User> getUsersFromCsv() {
		ArrayList<User> users = new ArrayList<>();
		List<List<String>> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("resources/users.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				records.add(Arrays.asList(values));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		records.forEach(e -> {
			User user = new User();
			user.setUsername(e.get(0));
			user.setPasshashFromString(e.get(3));
			user.setEmail(e.get(2));
			user.setPhone(e.get(1));
			users.add(user);
		} );
		return users;
	}
}
