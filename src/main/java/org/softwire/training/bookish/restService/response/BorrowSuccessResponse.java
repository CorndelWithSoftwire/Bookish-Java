package org.softwire.training.bookish.restService.response;

public class BorrowSuccessResponse implements Response{
    private int status;
    private int borrowId;

    public BorrowSuccessResponse(int setStatus, int setBorrowID) {
        this.borrowId = setBorrowID;
        this.status = setStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }
}
