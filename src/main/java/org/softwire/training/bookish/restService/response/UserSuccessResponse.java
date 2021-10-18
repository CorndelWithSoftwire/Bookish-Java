package org.softwire.training.bookish.restService.response;

import org.softwire.training.bookish.models.database.User;

public class UserSuccessResponse implements Response {
    private int status;
    private String username;

    public UserSuccessResponse(int setStatus, String setUsername) {
        this.status = setStatus;
        this.username = setUsername;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
