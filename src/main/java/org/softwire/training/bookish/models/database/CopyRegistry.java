package org.softwire.training.bookish.models.database;

public class CopyRegistry {
    private int id;
    private int bookId;
    private int borrowedBy;
    private String returnDate;

//    public CopyRegistry(int id, int bookId, int borrowedBy, String returnDate) {
//        this.id = id;
//        this.bookId = bookId;
//        this.borrowedBy = borrowedBy;
//        this.returnDate = returnDate;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(int borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
