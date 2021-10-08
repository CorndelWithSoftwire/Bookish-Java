package org.softwire.training.bookish;

import com.google.common.hash.Hashing;
import org.jdbi.v3.core.Jdbi;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;


public class Main {

    public static void main(String[] args) throws SQLException {
        jdbcMethod();
        //jdbiMethod(connectionString);
    }

    private static void jdbcMethod() throws SQLException {
        System.out.println("JDBC method...");

        // TODO: print out the details of all the books (using JDBC)
        // See this page for details: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html

        Properties connProperties = new Properties();
        connProperties.put("user", "root");
        connProperties.put("password", "c7f/SGXS<80D1H/Iqf0PQp90@dicw(J?");
        connProperties.setProperty("useSSL", "false");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookish", connProperties);

        Statement stmt = null;
        boolean rs = false;

        try {
            stmt = connection.createStatement();
            rs = stmt.execute("insert into Users values ('test_two', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', 'test_two@example.com', 07759545315)");
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { }

                stmt = null;
            }
        }
    }

    private static ArrayList<User> getUsersFromCsv() {
        return new ArrayList<org.softwire.training.bookish.Main.User>();
    }

    private static void jdbiMethod(String connectionString) {
        System.out.println("\nJDBI method...");

        // TODO: print out the details of all the books (using JDBI)
        // See this page for details: http://jdbi.org
        // Use the "Book" class that we've created for you (in the models.database folder)

        Jdbi jdbi = Jdbi.create(connectionString);
    }

    public class User {
        String username;
        String passhash;
        String email;
        String phone;

        public void setPasshashFromString(String password) {
            this.passhash = Hashing.sha256()
                    .hashString(password, StandardCharsets.UTF_8)
                    .toString();
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPasshash() {
            return passhash;
        }

        public void setPasshash(String passhash) {
            this.passhash = passhash;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
