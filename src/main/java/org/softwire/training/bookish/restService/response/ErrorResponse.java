package org.softwire.training.bookish.restService.response;

public class ErrorResponse implements Response {
    private int status;
    private String message;

    public ErrorResponse(int setStatus, String setMessage) {
        this.status = setStatus;
        this.message = setMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
