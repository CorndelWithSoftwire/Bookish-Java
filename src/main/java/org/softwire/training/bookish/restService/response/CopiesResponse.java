package org.softwire.training.bookish.restService.response;

import org.softwire.training.bookish.models.database.Copies;
import org.softwire.training.bookish.models.database.Copy;

import java.util.List;

public class CopiesResponse implements Response{
    private int status;
    private List<Copy> copies;

    public CopiesResponse(int setStatus, List<Copy> setCopies) {
       status = setStatus;
       copies = setCopies;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Copy> getCopies() {
        return copies;
    }

    public void setCopies(List<Copy> copies) {
        this.copies = copies;
    }
}
