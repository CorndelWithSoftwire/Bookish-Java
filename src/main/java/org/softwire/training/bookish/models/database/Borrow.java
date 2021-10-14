package org.softwire.training.bookish.models.database;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.execeptions.NoBorrowsException;
import org.softwire.training.bookish.models.dao.BorrowDao;

import java.util.Date;
import java.util.List;

public class Borrow {
	private int borrowId;
	private int borrowCopyId;
	private String username;
	private Date checkOutDate;
	private Date checkInDate;
	private Date dueDate;

	public Borrow() {};

	public int getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(int borrowId) {
		this.borrowId = borrowId;
	}

	public int getBorrowCopyId() {
		return borrowCopyId;
	}

	public void setBorrowCopyId(int borrowCopyId) {
		this.borrowCopyId = borrowCopyId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public void insertIntoDatabase(Jdbi jdbi) {
		jdbi.useExtension(BorrowDao.class, dao -> dao.insertBorrow(this.borrowId, this.borrowCopyId, this.username, this.checkOutDate, this.checkInDate, this.dueDate));
	}

	public List<Borrow> queryByUsername(Jdbi jdbi, String username) throws NoBorrowsException {
		List<Borrow> borrows = jdbi.withExtension(BorrowDao.class, dao -> dao.getUsersBorrows(username));
		if (borrows.isEmpty()) {
			throw new NoBorrowsException("There are no borrows for this user");
		}
		return borrows;
	}

	public List<Borrow> queryById(Jdbi jdbi, int id) throws NoBorrowsException {
		List<Borrow> borrows = jdbi.withExtension(BorrowDao.class, dao -> dao.getBorrowById(id));
		if (borrows.isEmpty()) {
			throw new NoBorrowsException("There are no borrows for this user");
		}
		return borrows;
	}
}
