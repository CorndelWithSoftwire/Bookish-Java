package org.softwire.training.bookish.restService.response;

public class SuccessfulLoginResponse implements Response {
    private  int status;
    private  String jwt;

    public SuccessfulLoginResponse(int status, String jwt) {
        this.status = status;
        this.jwt = jwt;
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
