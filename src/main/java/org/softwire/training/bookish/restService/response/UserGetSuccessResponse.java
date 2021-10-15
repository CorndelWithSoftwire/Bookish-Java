package org.softwire.training.bookish.restService.response;

import org.softwire.training.bookish.models.database.User;

public class UserGetSuccessResponse implements Response {
    private int status;
    private User user;

    public UserGetSuccessResponse(int setStatus, User setUser) {
        status = setStatus;
        user = setUser;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
