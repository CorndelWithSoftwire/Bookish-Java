package org.softwire.training.bookish;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.softwire.training.bookish.models.database.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws SQLException {
        String hostname = "localhost";
        String database = "bookish";
        String user = "bookish";
        String password = "bookish";
        String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";

        jdbcMethod(connectionString);
        getBookCopies(connectionString);
        listOfAvailableBooks(connectionString);
        jdbiMethod(connectionString);
        getOwners(connectionString);
        searchForBookTitle(connectionString);
    }

    private static void jdbcMethod(String connectionString) throws SQLException {
        System.out.println("JDBC method...");
        ResultSet rs = makingTheConnection(connectionString,"SELECT * FROM book");
        int count = 1;
        while(rs.next()){
            String book = rs.getString("title");
            System.out.println("book " + count + " " + book);
            count++;
        }
    }

    private static void getBookCopies(String connectionString) throws SQLException {
        System.out.println("JDBC method...");
        ResultSet rs = makingTheConnection(connectionString,"select * from book");
        while(rs.next()) {
            String title = rs.getString("title");
            System.out.println("Copies in the library for " + title + " is: " + rs.getInt("number_of_copies"));
        }

    }

    private static void listOfAvailableBooks(String connectionString) throws SQLException{
        ArrayList<String> arrayOfBooksAvailable = new ArrayList<>();
        ResultSet rs = makingTheConnection(connectionString,"select * from book, copy_registry cr where book.id = cr.book_id and cr.borrowed_by is null");
        while (rs.next()){
            arrayOfBooksAvailable.add(rs.getString("title"));
        }
        System.out.println("Available books:");
        for (String s : arrayOfBooksAvailable) {
            System.out.println(s);
        }
    }

    private static void jdbiMethod(String connectionString) {
        System.out.println("\nJDBI method...");
        Jdbi jdbi = Jdbi.create(connectionString);
        System.out.println("jdbi book test");
        List<Book> bookList = jdbi.withHandle(handle ->
              handle.createQuery("SELECT * FROM book")
                      .mapToBean(Book.class)
                      .list()
        );
        for (Book b: bookList) {
            System.out.printf("Title: %s, author ID: %s%n", b.getTitle(), b.getAuthorId());
        }
      
      
        System.out.println("jdbi check user loan test");
        List<CopyRegistry> loanList = jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM copy_registry")
                        .mapToBean(CopyRegistry.class)
                        .list()
        );

        for (CopyRegistry loans: loanList) {
            if (loans.getBorrowedBy() != 0)
            System.out.printf("Book ID: %s, borrowed by ID: %s%n", loans.getBookId(), loans.getBorrowedBy());
        }


    }
    private static void getOwners(String connectionString) {
        Jdbi jdbi = Jdbi.create(connectionString);
        jdbi.installPlugin(new SqlObjectPlugin());

        List<User> users = jdbi.withExtension(
                UserDao.class, UserDao::listLoanUsers
        );
      
       System.out.println("Users From DAO " + users);

    }

    private static void searchForBookTitle(String connectionString) {
        Jdbi jdbi = Jdbi.create(connectionString);
        jdbi.installPlugin(new SqlObjectPlugin());

        List<Author> authors = jdbi.withExtension(
                BookDao.class, dao -> dao.listAuthorAndBooks("%"+ "the" +"%")
        );

        System.out.println("Books From DAO " + authors);
    }

    public static ResultSet makingTheConnection(String connectionString, String sql) throws SQLException {
        Connection connection = DriverManager.getConnection(connectionString);
        Statement booksStmt = connection.createStatement();
        return booksStmt.executeQuery(sql);
    }
}
