package com.webianks.educorp;

/**
 * Created by R Ankit on 14-01-2017.
 */
public class GeneralResponse {

    String status;
    String message;
    String error;

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
