package org.softwire.training.bookish;

import org.jdbi.v3.core.Jdbi;

import java.sql.*;


public class Main {

    public static void main(String[] args) throws SQLException {
        String hostname = "localhost";
        String database = "CAT_DATABASE";
        String user = "bookish";
        String password = "bookish";
        String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";

        jdbcMethod(connectionString);
        jdbiMethod(connectionString);
    }

    private static void jdbcMethod(String connectionString) throws SQLException {
        System.out.println("JDBC method...");

        // TODO: print out the details of all the books (using JDBC)
        // See this page for details: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html

        Connection connection = DriverManager.getConnection(connectionString);
        String query = "SELECT * FROM cats";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()) {
            String name = rs.getString("name");
            String age = rs.getString("age");
            System.out.println(name + " with age " + age);
        }



    }

    private static void jdbiMethod(String connectionString) {
        System.out.println("\nJDBI method...");

        // TODO: print out the details of all the books (using JDBI)
        // See this page for details: http://jdbi.org
        // Use the "Book" class that we've created for you (in the models.database folder)

        Jdbi jdbi = Jdbi.create(connectionString);



    }
}
