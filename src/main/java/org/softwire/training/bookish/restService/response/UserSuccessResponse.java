package org.softwire.training.bookish.restService.response;

import org.softwire.training.bookish.models.database.User;

public class UserSuccessResponse implements Response {
    private int status;
    private String username;

    public UserSuccessResponse(int setStatus, String setUsername) {
        this.status = setStatus;
        this.username = setUsername;
    }
}
