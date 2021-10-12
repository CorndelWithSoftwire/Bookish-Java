package org.softwire.training.bookish.models.database;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "CopyRegistry{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", borrowedBy=" + borrowedBy +
                ", returnDate='" + returnDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CopyRegistry that = (CopyRegistry) o;
        return id == that.id && bookId == that.bookId && borrowedBy == that.borrowedBy && Objects.equals(returnDate, that.returnDate);
    }



    @Override
    public int hashCode() {
        return Objects.hash(id, bookId, borrowedBy, returnDate);
    }
}
