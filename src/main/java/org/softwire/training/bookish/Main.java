package org.softwire.training.bookish;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.CopyRegistry;
import org.softwire.training.bookish.models.database.Technology;
import org.softwire.training.bookish.models.database.User;

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
        getBookCopies(connectionString, listOfAvailableBooks(connectionString));
        listOfAvailableBooks(connectionString);
        jdbiMethod(connectionString);
    }

    private static void jdbcMethod(String connectionString) throws SQLException {
        System.out.println("JDBC method...");

        // TODO: print out the details of all the books (using JDBC)
        // See this page for details: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html

        Connection connection = DriverManager.getConnection(connectionString);
        String selectingFromBooks = "SELECT * FROM book";
        Statement booksStmt = connection.createStatement();
        ResultSet bookRs = booksStmt.executeQuery(selectingFromBooks);
        int count = 1;
        while(bookRs.next()){
            String book = bookRs.getString("title");
            System.out.println("book " + count + " " + book);
            count++;
        }


    }

    private static void getBookCopies(String connectionString, ArrayList<Integer> borrowedBooks) throws SQLException {
        System.out.println("JDBC method...");

        // TODO: print out the details of all the books (using JDBC)
        // See this page for details: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html

        Connection connection = DriverManager.getConnection(connectionString);
        String query = "select * from book";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()) {
            String title = rs.getString("title");
            int numberOfCopies = rs.getInt("number_of_copies");
            int idOfBook = rs.getInt("id");
            if(borrowedBooks.contains(idOfBook)){
                numberOfCopies -= 1;
            }
            System.out.println(title +  " has " + numberOfCopies + " copies available.");
        }
    }

    private static ArrayList<Integer> listOfAvailableBooks(String connectionString) throws SQLException{
        ArrayList<Integer> arrayOfBooksBorrowed = new ArrayList<>();
        Connection connection = DriverManager.getConnection(connectionString);
        String query = "select * from copy_registry";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()){
            if(rs.getString("borrowed_by") != null){
                arrayOfBooksBorrowed.add(rs.getInt("book_id"));
            }
        }
        return arrayOfBooksBorrowed;
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




    }
}
