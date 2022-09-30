//package org.softwire.training.bookish;
//
//import org.jdbi.v3.core.Handle;
//import org.jdbi.v3.core.Jdbi;
//import org.jdbi.v3.core.statement.SqlParser;
//import org.softwire.training.bookish.models.database.Book;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class Main {
//
//    public static void main(String[] args) throws SQLException {
//        String hostname = "localhost";
//        String database = "Library";
//        String user = "root";
//        String password = "%20";
//        String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";
//
//        jdbcMethod(connectionString);
//        jdbiMethod(connectionString);
//    }
//
//    private static void jdbcMethod(String connectionString) throws SQLException {
//        System.out.println("JDBC method...");
//
//        // See this page for details: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
//
//        Connection connection = DriverManager.getConnection(connectionString);
//        Statement statement = connection.createStatement();
//        String query = "SELECT * FROM Books ORDER BY Title ASC";
//        ResultSet results = statement.executeQuery(query);
//        List<Book> books = new ArrayList<>();
//        while (results.next()){
//            books.add(new Book());//results.getInt(1), results.getString(2), results.getString(3),
//                    //results.getString(4), results.getString(5),
//                    //results.getInt(6)));
//        }
//        System.out.println(books.get(0).getTitle());
//
//    }
//
//    private static void jdbiMethod(String connectionString) {
//        System.out.println("\nJDBI method...");
//
//        // See this page for details: http://jdbi.org
//        // Use the "Book" class that we've created for you (in the models.database folder)
//
//        Jdbi jdbi = Jdbi.create(connectionString);
//        List<Book> books = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM Books")
//                .map((rs, ctx) -> new Book()).list());//rs.getInt("ID"), rs.getString("Title"),
//                        //rs.getString("Author"), rs.getString("ISBN"), rs.getString("genre"),
//                        //rs.getInt("copies"))).list());
//        System.out.println(books.get(0).getTitle());
//
//
//
//    }
//}
