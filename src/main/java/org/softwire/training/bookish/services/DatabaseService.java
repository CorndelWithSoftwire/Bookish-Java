package org.softwire.training.bookish.services;

import org.jdbi.v3.core.Jdbi;

public abstract class DatabaseService {

    private static final String hostname = "localhost";
    private static final String database = "Library";
    private static final String user = "root";
    private static final String password = "%20";
    private static final String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";

    protected static final Jdbi jdbi = Jdbi.create(connectionString);
}
