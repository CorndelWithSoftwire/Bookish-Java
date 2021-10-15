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

		String[] tables = {"Authors", "Book", "BookAuthor", "Borrows", "Copies", "Librarians", "Users"};

		jdbi.useHandle(handle -> {
			handle.execute("SET FOREIGN_KEY_CHECKS=0");
			Arrays.stream(tables).forEach(table -> {
				handle.execute("drop table "+table);
			});
			handle.execute("SET FOREIGN_KEY_CHECKS=1");
			handle.execute("CREATE TABLE Book (BookId int NOT NULL AUTO_INCREMENT, Title varchar(255)  NOT NULL, CreatedAt date  NOT NULL, UpdatedAt date  NOT NULL, Slug varchar(255)  NOT NULL, Isbn varchar(255)  NULL, Subtitle varchar(255)  NULL, Subjects varchar(600)  NULL, CoverPhotoUrl varchar(255) NULL, PRIMARY KEY (BookId));");
			handle.execute("CREATE TABLE Copies (CopyId int  NOT NULL AUTO_INCREMENT, BookId int  NOT NULL , PRIMARY KEY (CopyId));");
			handle.execute("CREATE TABLE BookAuthor (Book int  NOT NULL, Author int  NOT NULL);");
			handle.execute("CREATE TABLE Authors (AuthorId int  NOT NULL AUTO_INCREMENT, AuthorName varchar(255)  NOT NULL, PRIMARY KEY (AuthorId));");
			handle.execute("CREATE TABLE Users (Username varchar(32)  NOT NULL ,ProfilePicUrl varchar(255),PasswordHash varchar(255)  NOT NULL ,Email varchar(255)  NOT NULL ,PhoneNumber long  NOT NULL ,PRIMARY KEY (Username));");
			handle.execute("CREATE TABLE Librarians (Username varchar(32)  NOT NULL ,PRIMARY KEY (Username));");
			handle.execute("CREATE TABLE Borrows (BorrowId int  NOT NULL AUTO_INCREMENT, BorrowedCopyId int  NOT NULL ,Username varchar(32)  NOT NULL ,CheckOutDate date  NOT NULL ,CheckInDate date, DueDate date  NOT NULL, PRIMARY KEY (BorrowId));");
			handle.execute("ALTER TABLE BookAuthor ADD CONSTRAINT fk_Book_BookId FOREIGN KEY(Book) REFERENCES Book (BookId);");
			handle.execute("ALTER TABLE Borrows ADD CONSTRAINT fk_Copies_CopyId FOREIGN KEY(BorrowedCopyId) REFERENCES Copies (CopyId);");
			handle.execute("ALTER TABLE Copies ADD CONSTRAINT fk_Copies_BookId FOREIGN KEY(BookId) REFERENCES Book (BookId);");
			handle.execute("ALTER TABLE BookAuthor ADD CONSTRAINT fk_Authors_AuthorId FOREIGN KEY(Author) REFERENCES Authors (AuthorId);");
			handle.execute("ALTER TABLE Librarians ADD CONSTRAINT fk_Librarians_Username FOREIGN KEY(Username) REFERENCES Users (Username);");
			handle.execute("ALTER TABLE Borrows ADD CONSTRAINT fk_Borrows_Username FOREIGN KEY(Username) REFERENCES Users (Username);");
			handle.execute("CREATE INDEX idx_Book_Title ON Book (Title);");
			handle.execute("CREATE INDEX idx_Authors_Name ON Authors (AuthorName);");
		});


		populateUsers(jdbi);
		makeLibrarians(jdbi);

		Books allBooks = new Books("resources/books.csv");
		Authors allAuthors = new Authors(allBooks);
		populateAuthors(jdbi, allAuthors);

		populateBooks(jdbi, allBooks);
		List<BookAuthor> bookAuthor = createBookAuthors(allBooks, allAuthors);

		populateBookAuthors(jdbi, bookAuthor);
		populateCopies(jdbi, allBooks);

		populateBorrows(jdbi);
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
				copy.setBookId(b.getBookID().get());
				copy.setCopyId(id.getAndIncrement());
				copy.insertCopyIntoDb(jdbi);
			}
		});
	}

	private static void populateBookAuthors(Jdbi jdbi, List<BookAuthor> bookAuthor) {
		bookAuthor.forEach(bookAuthor1 -> bookAuthor1.insertBookAuthor(jdbi));
	}

	private static List<BookAuthor> createBookAuthors(Books allBooks, Authors allAuthors) {
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
				tempAuthorList.add(bookAuthor.getAuthorId().get());
				bookAuthorSet.put(book.getBookID().get(), tempAuthorList);
			}
		});
		List<BookAuthor> bookAuthorList = new ArrayList<>();
		bookAuthorSet.keySet().forEach(key -> {
			System.out.println(key);
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

	private static void populateBooks(Jdbi jdbi, Books allBooks) {
		allBooks.booksList.forEach(book -> {
			book.setBookID(book.insertBook(jdbi));
		});
	}

	private static void populateAuthors(Jdbi jdbi, Authors allAuthors) {
		allAuthors.getAuthors().forEach(author -> {
			author.setAuthorId(author.insertIntoDb(jdbi));
			System.out.println(author.getAuthorId().get());
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