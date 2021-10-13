package org.softwire.training.bookish.models.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.softwire.training.bookish.models.database.Copy;

import java.util.List;

public interface CopyDao {
	@SqlQuery("SELECT * FROM Copies WHERE BookId=:bookid")
	@RegisterBeanMapper(Copy.class)
	List<Copy> getBookCopies(@Bind("bookid") int bookId);

	@SqlUpdate("INSERT INTO Copies VALUES (?, ?)")
	void insertCopy(int copyId, int bookId);

	@SqlQuery("select COUNT(CopyId) from Copies where BookId=:bookid")
	int getNumberOfCopies(@Bind("bookid") int bookId);
}
