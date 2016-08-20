package me.thenpush.rest.models;

/**
 * Created by pappacena on 20/08/16.
 */

public class ResponseMessage {
    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
