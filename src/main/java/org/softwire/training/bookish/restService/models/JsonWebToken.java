package org.softwire.training.bookish.restService.models;

import org.softwire.training.bookish.models.database.User;

public class JsonWebToken {
    private final String token;
    private final String userName;

    public JsonWebToken(String fakeToken, User userPayload) {
        this.token = fakeToken;
        userName = userPayload.getUsername();
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "JsonWebToken{" +
                "token='" + token + '\'' +
                ", username=" + userName +
                '}';
    }


    public String getUserName() {
        return userName;
    }
}
