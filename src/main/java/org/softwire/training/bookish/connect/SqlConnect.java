package org.softwire.training.bookish.connect;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.Properties;

public class SqlConnect {
    public static Jdbi createJdbiConnection() {
        Properties connProperties = new Properties();
        connProperties.put("user", System.getenv("SQL_UNAME"));
        connProperties.put("password", System.getenv("SQL_PASS"));
        connProperties.setProperty("useSSL", "false");
        String connectionUrl = String.format("jdbc:mysql://%s:3306/%s", System.getenv("SQL_HOST"), System.getenv("SQL_DB"));
        Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/bookish", connProperties);
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }
}
