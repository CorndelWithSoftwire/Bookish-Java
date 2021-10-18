package org.softwire.training.bookish.restService.response;

public class SuccessfulBookCreation implements Response {
    private int status;
    private String responseMessage;

    public SuccessfulBookCreation(int status, String responseMessage) {
        this.status = status;
        this.responseMessage = responseMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
