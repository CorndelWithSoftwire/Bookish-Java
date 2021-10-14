package org.softwire.training.bookish.models.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Borrow;

import java.util.Date;
import java.util.List;

public interface BorrowDao {
	@SqlUpdate("INSERT INTO Borrows(borrowedcopyid, username, checkoutdate, checkindate, duedate) VALUES (?, ?, ?, ?, ?)")
	@GetGeneratedKeys
	int insertBorrow(int borrowedCopy, String username, Date checkOutDate, Date checkInDate, Date DueDate);

	@SqlQuery("SELECT * FROM Borrows WHERE Username=:username")
	@RegisterBeanMapper(Borrow.class)
	List<Borrow> getUsersBorrows(@Bind("username") String username);

	@SqlQuery("SELECT * FROM Borrows WHERE BorrowId=:id")
	@RegisterBeanMapper(Borrow.class)
	List<Borrow> getBorrowById(@Bind("id") int id);

	@SqlQuery("SELECT * FROM Borrows WHERE BorrowedCopyId=:borrowCopyId")
	@RegisterBeanMapper(Borrow.class)
	List<Borrow> getBorrowsByCopy(@Bind("borrowCopyId") int borrowCopyId);

	@SqlQuery("select * from Book where BookId = (select BookId from Copies where CopyId=(select BorrowedCopyId from Borrows where Username=:username))")
	@RegisterBeanMapper(Book.class)
	List<Book> getUsersBorrowedBooks(@Bind("username")String username);
}
