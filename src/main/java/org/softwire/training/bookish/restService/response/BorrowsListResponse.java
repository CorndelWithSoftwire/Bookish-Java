package org.softwire.training.bookish.restService.response;

import org.softwire.training.bookish.models.database.Borrow;

import java.util.List;

public class BorrowsListResponse implements Response {
    private int status;
    private List<Borrow> borrows;

    public BorrowsListResponse(int status, List<Borrow> borrows) {
        this.status = status;
        this.borrows = borrows;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Borrow> getBorrows() {
        return borrows;
    }

    public void setBorrows(List<Borrow> borrows) {
        this.borrows = borrows;
    }
}
