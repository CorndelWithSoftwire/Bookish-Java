package org.softwire.training.bookish.restService.response;

public class SuccessfulLoginResponse implements Response {
    private int status;
    private String jwt;
    private String username;

    public SuccessfulLoginResponse(int status, String jwt, String username) {
        this.status = status;
        this.jwt = jwt;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
