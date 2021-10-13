package org.softwire.training.bookish.models.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.softwire.training.bookish.models.database.Borrow;

import java.util.List;

public interface BorrowDao {
	@SqlUpdate("INSERT INTO Borrows VALUES (?, ?, ?, ?, ?, ?)")
	void insertBorrow(int borrowId, int borrowedCopy, String username, String checkOutDate, String checkInDate, String DueDate);

	@SqlQuery("SELECT * FROM Borrows WHERE Username=:username")
	@RegisterBeanMapper(Borrow.class)
	List<Borrow> getUsersBorrows(@Bind("username") String username);
}
