package org.softwire.training.bookish.restService.response;

public class SuccessfulBookCreation implements  Response{
    private int status;
    private String responseMessage;

    public SuccessfulBookCreation(int status, String responseMessage) {
        this.status = status;
        this.responseMessage = responseMessage;
    }
}
