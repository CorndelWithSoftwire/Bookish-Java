package org.softwire.training.bookish;

import com.google.common.hash.Hashing;
import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.database.Authors;
import org.softwire.training.bookish.models.database.Books;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class Main {

    public static void main(String[] args) throws SQLException {
//        jdbcMethod();
        //jdbiMethod(connectionString);

        Books myBooks = new Books("resources/books.csv");

        myBooks.booksList.stream().filter(book -> book.getAuthors().isEmpty()).forEach(System.out::println);
        Set<String> myAuthors = new TreeSet<String>();
        Authors aBunchOfAuthors = new Authors(myBooks);
        System.out.println(aBunchOfAuthors.getAuthors().toArray()[0]);
        /*
        Todo:
            - Connect to Database.
            - Populate Database.
        Todo:
            - Create Copies of Books
            - Populate Database
         */
    }

    private static void jdbcMethod() throws SQLException {
        System.out.println("JDBC method...");

        // TODO: print out the details of all the books (using JDBC)
        // See this page for details: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html

        Properties connProperties = new Properties();
        connProperties.put("user", "root");
        connProperties.put("password", "c7f/SGXS<80D1H/Iqf0PQp90@dicw(J?"); // change to universal password for everyone's db
        connProperties.setProperty("useSSL", "false");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookish", connProperties);

        Statement stmt = null;
        boolean rs = false;
        ArrayList<User> users = getUsersFromCsv();
//        ArrayList<Books> books = getBooksFromCsv();

        for (User user : users) {
            try {
                stmt = connection.createStatement();
                rs = stmt.execute("insert into Users values (" + user.getUsername() + "," + user.getPasshash() + ", " + user.getEmail() + "," + user.getPhone() + ")");
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException sqlEx) {
                    }

                    stmt = null;
                }
            }
        }

    }

    private static ArrayList<User> getUsersFromCsv() {
        List<User> users = new ArrayList<>();
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
        });
        return (ArrayList<User>) users;
    }

    private static void jdbiMethod(String connectionString) {
        System.out.println("\nJDBI method...");

        // TODO: print out the details of all the books (using JDBI)
        // See this page for details: http://jdbi.org
        // Use the "Book" class that we've created for you (in the models.database folder)

        Jdbi jdbi = Jdbi.create(connectionString);
    }

    public static class User {
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
