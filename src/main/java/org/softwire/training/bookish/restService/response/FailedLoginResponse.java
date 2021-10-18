package org.softwire.training.bookish.restService.response;

public class FailedLoginResponse implements Response {
    private  int status;
    private  String responseData;

    public FailedLoginResponse(int value, String message) {
        this.status = value;
        this.responseData = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }
}
