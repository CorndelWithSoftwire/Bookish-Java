package org.softwire.training.bookish.models.database;

public class Borrow {
	private int borrowId;
	private int borrowCopyId;
	private String username;
	private String checkOutDate;
	private String checkInDate;
	private String dueDate;

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

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
}
