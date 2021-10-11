package org.softwire.training.bookish;

import com.google.common.hash.Hashing;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.UnableToCreateStatementException;

import javax.lang.model.type.ArrayType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


public class Main {

    public static void main(String[] args) throws SQLException {
        Properties connProperties = new Properties();
        connProperties.put("user", "root");
        connProperties.put("password", "c7f/SGXS<80D1H/Iqf0PQp90@dicw(J?");
        connProperties.setProperty("useSSL", "false");
        Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/bookish", connProperties);
        jdbcMethod(jdbi);
        makeLibrarians(jdbi);
        //jdbiMethod(connectionString);
    }

    private static void makeLibrarians(Jdbi jdbi) throws SQLException {

        List<String> librarians = Arrays.asList("Sears", "Kent", "Merrill");
        for (String librarian: librarians) {
            jdbi.withHandle(handle -> {
                return handle.execute("insert into Librarians values (?)", librarian);
            });
        }

    }

    private static void jdbcMethod(Jdbi jdbi) throws SQLException {
        ArrayList<User> users = getUsersFromCsv();
        for (User user : users){
                jdbi.withHandle(handle -> {
                    try {
                        return handle.execute("insert into Users values (?, ?, ?, ?)", user.getUsername(), user.getPasshash(), user.getPasshash(), user.getPhone());
                    } catch (UnableToCreateStatementException e) {
                        return null;
                    }
                });
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
            user.setPhone(e.get(1));
            users.add(user);
        } );
        return users;
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