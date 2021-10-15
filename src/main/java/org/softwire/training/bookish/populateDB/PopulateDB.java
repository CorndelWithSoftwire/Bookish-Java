package org.softwire.training.bookish.populateDB;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.softwire.training.bookish.models.dao.AuthorDao;
import org.softwire.training.bookish.models.dao.BookDao;
import org.softwire.training.bookish.models.database.*;
import org.softwire.training.bookish.models.database.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PopulateDB {
	public static void main(String[] args) throws SQLException {
		Jdbi jdbi = createJdbiConnection();

		Books allBooks = new Books("resources/books.csv");
//		populateBooks(jdbi, allBooks);

//		populateUsers(jdbi);
//		makeLibrarians(jdbi);


//		Authors allAuthors = new Authors(allBooks);
//		List<BookAuthor> bookAuthor = createBookAuthors(allBooks, allAuthors);
//
//		populateAuthors(jdbi, allAuthors);
//
//		populateBookAuthors(jdbi, bookAuthor);
//		populateCopies(jdbi, allBooks);
		/*
		 */
//		populateBorrows(jdbi);


		/*
		List<Author> authorObj = jdbi.withExtension(AuthorDao.class, dao -> dao.getAuthorByName("  Andrew Glover"));
		System.out.println(authorObj);


		List<User> users = jdbi.withExtension(UserDao.class, dao -> dao.getUser("Test3"));
		System.out.println(users);

		List<Librarian> librarians = jdbi.withExtension(LibrarianDao.class, dao -> {
			//dao.createLibrarian("Test3");
			return dao.getLibrarian("Test3");
		});
		System.out.println(librarians);

//		List<User> users = jdbi.withExtension(UserDao.class, dao -> {
//			dao.insertUser("Test3","null","do@email.com","909876654");
//		return null;
//		});
//		System.out.println(users);
		User user3 = new User();
		user3.setUsername("bec");
		user3.setEmail("bec@gmail.com");
		user3.setPasshashFromString("hello");
		user3.setPhoneNumber("98765433");
		user3.insertUserToDatabase(jdbi);
//
//		List<Librarian> librarians = jdbi.withExtension(LibrarianDao.class, dao -> {
//			//dao.createLibrarian("Test3");
//			return dao.getLibrarian("Test3");
//		});
//		System.out.println(librarians);

		User user = new User();
		user.getUserFromDatabase(jdbi, "bec" );
		System.out.println(user);
*/
	}

	public static Jdbi createJdbiConnection() {
		Properties connProperties = new Properties();
		connProperties.put("user", "root");
		connProperties.put("password", "c7f/SGXS<80D1H/Iqf0PQp90@dicw(J?");
		connProperties.setProperty("useSSL", "false");
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/bookish", connProperties);
		jdbi.installPlugin(new SqlObjectPlugin());
		return jdbi;
	}

	private static void populateBorrows(Jdbi jdbi) {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 10);
		Borrow borrow = new Borrow();
		borrow.setBorrowId(0);
		borrow.setBorrowCopyId(348);
		borrow.setUsername("Anthony");
		borrow.setCheckOutDate(date);
		borrow.setDueDate(c.getTime());
		borrow.setCheckInDate(null);
		borrow.insertIntoDatabase(jdbi);
	}

	private static void populateCopies(Jdbi jdbi, Books allBooks) {
		AtomicInteger id = new AtomicInteger();
		Random rand = new Random();
		allBooks.booksList.forEach(b -> {
			int numOfCopies = rand.nextInt(5);
			for (int copyNum = 0; copyNum <= numOfCopies; copyNum++) {
				Copy copy = new Copy();
				copy.setBookId(b.getBookID());
				copy.setCopyId(id.getAndIncrement());
				copy.insertCopyIntoDb(jdbi);
			}
		});
	}

	public static void populateBookAuthors(Jdbi jdbi, List<BookAuthor> bookAuthor) {
		bookAuthor.forEach(bookAuthor1 -> bookAuthor1.insertBookAuthor(jdbi));
	}

	public static List<BookAuthor> createBookAuthors(Books allBooks, Authors allAuthors) {
		HashMap<Integer, ArrayList<Integer>> bookAuthorSet = new HashMap<>();
		Set<Author> authors = allAuthors.getAuthors();
		allBooks.booksList.forEach(book -> {
			String bookAuthors = book.getAuthors();
			List<String> individualAuthors = Arrays.stream(bookAuthors.split(",")).map(a -> recursiveTrim(a.replaceAll("\"", ""))).collect(Collectors.toList());
			for (String author : individualAuthors) {
				List<Author> collectedAuthors = authors.stream().filter(e -> e.getAuthorName().equals(author.trim())).collect(Collectors.toList());
				Author bookAuthor = collectedAuthors.get(0);
				ArrayList<Integer> tempAuthorList = bookAuthorSet.get(bookAuthor.getAuthorId());
				if (tempAuthorList == null) {
					tempAuthorList = new ArrayList<>();
				}
				tempAuthorList.add(bookAuthor.getAuthorId());
				bookAuthorSet.put(book.getBookID(), tempAuthorList);
			}
		});
		List<BookAuthor> bookAuthorList = new ArrayList<>();
		bookAuthorSet.keySet().forEach(key -> {
			ArrayList<Integer> authorsList = bookAuthorSet.get(key);
			authorsList.forEach(auth -> {
				BookAuthor bookAuthor = new BookAuthor();
				bookAuthor.setAuthor(auth);
				bookAuthor.setBook(key);
				bookAuthorList.add(bookAuthor);
			});
		});
		return bookAuthorList;
	}

	private static String recursiveTrim(String string) {
		while (string.charAt(0) == ' ' || string.charAt(string.length()-1) == ' ') {
			string = string.trim();
		}
		return string;
	}

	public static void populateBooks(Jdbi jdbi, Books allBooks) {
		Integer count = jdbi.withExtension(BookDao.class, dao -> {
			for (Book each : allBooks.booksList) {
				dao.insertBook(
						each.getBookID(),
						each.getTitle(),
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

	public static void populateAuthors(Jdbi jdbi, Authors allAuthors) {
		Integer count = jdbi.withExtension(AuthorDao.class, dao -> {
			for (Author each : allAuthors.getAuthors()) {
				dao.insertAuthors(
						each.getAuthorId(),
						each.getAuthorName()
				);
			}
			;
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

	private static void populateUsers(Jdbi jdbi) throws SQLException {
		ArrayList<User> users = getUsersFromCsv();
		for (User user : users){
			user.insertUserToDatabase(jdbi);
		}
	}

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