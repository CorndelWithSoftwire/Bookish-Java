package org.softwire.training.bookish;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.CopyRegistry;
import org.softwire.training.bookish.models.database.Technology;
import org.softwire.training.bookish.models.database.User;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.softwire.training.bookish.models.database.*;

import java.sql.*;
import java.util.*;


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
    }

    private static void jdbcMethod(String connectionString) throws SQLException {
        System.out.println("JDBC method...");

        // TODO: print out the details of all the books (using JDBC)
        // See this page for details: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
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

        // TODO: print out the details of all the books (using JDBC)
        // See this page for details: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html

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

        // TODO: print out the details of all the books (using JDBI)
        // See this page for details: http://jdbi.org
        // Use the "Book" class that we've created for you (in the models.database folder)

        Jdbi jdbi = Jdbi.create(connectionString);

        /*
        book test
         */
        System.out.println("jdbi book test");
        List<Book> bookList = jdbi.withHandle(handle ->
              handle.createQuery("SELECT * FROM book")
                      .mapToBean(Book.class)
                      .list()
        );
        // print book title and author id
        for (Book b: bookList) {
            System.out.println(String.format("Title: %s, author ID: %s", b.getTitle(), b.getAuthorID()));
        }
      
      
        System.out.println("jdbi check user loan test");
        List<CopyRegistry> loanList = jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM copy_registry")
                        .mapToBean(CopyRegistry.class)
                        .list()
        );

        for (CopyRegistry loans: loanList) {
            if (loans.getBorrowedBy() != 0)
            System.out.println(String.format("Book ID: %s, borrowed by ID: %s", loans.getBookId(), loans.getBorrowedBy()));
        }


    }
    private static void getOwners(String connectionString) {
        Jdbi jdbi = Jdbi.create(connectionString);
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin()); // usually when connecting

        List<User> users = jdbi.withExtension(
                UserDao.class, dao -> {
                    return dao.listLoanUsers();
                }
        );
      
       System.out.println("Users From DAO " + users);
    }

    public static ResultSet makingTheConnection(String connectionString, String sql) throws SQLException {
        Connection connection = DriverManager.getConnection(connectionString);
        String selectingFromBooks = sql;
        Statement booksStmt = connection.createStatement();
        ResultSet sqlRs = booksStmt.executeQuery(selectingFromBooks);
        return sqlRs;
    }
}
