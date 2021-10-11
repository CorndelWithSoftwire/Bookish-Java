package org.softwire.training.bookish.models.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.softwire.training.bookish.models.database.Librarian;

import java.util.List;

public interface LibrarianDao {

	@SqlUpdate("INSERT INTO Librarians Values (:username)")
	void createLibrarian(@Bind("username") String username);

	@SqlQuery("SELECT * FROM Librarians WHERE Username=:username")
	@RegisterBeanMapper(Librarian.class)
	List<Librarian> getLibrarian(@Bind("username") String username);

	@SqlQuery("SELECT * FROM Librarians")
	@RegisterBeanMapper(Librarian.class)
	List<Librarian> getLibrarians();
}
