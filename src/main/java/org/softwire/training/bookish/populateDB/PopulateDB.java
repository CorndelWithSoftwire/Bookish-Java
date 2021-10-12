package org.softwire.training.bookish.populateDB;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.softwire.training.bookish.models.database.User;

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

		/*
		populateUsers(jdbi);
		makeLibrarians(jdbi);
		*/

//		List<User> users = jdbi.withExtension(UserDao.class, dao -> {
//			dao.insertUser("Test3","null","do@email.com","909876654");
//		return null;
//		});
//		System.out.println(users);
		User user2 = new User();
		user2.setUsername("bec");
		user2.setEmail("bec@gmail.com");
		user2.setPasshashFromString("hello");
		user2.setPhoneNumber("98765433");
		user2.insertUserToDatabase(jdbi);
//
//		List<Librarian> librarians = jdbi.withExtension(LibrarianDao.class, dao -> {
//			//dao.createLibrarian("Test3");
//			return dao.getLibrarian("Test3");
//		});
//		System.out.println(librarians);

		User user = new User();
		user.getUserFromDatabase(jdbi, "bec" );
		System.out.println(user);

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
			user.setPhoneNumber(e.get(1));
			users.add(user);
		} );
		return users;
	}
}
