package com.pazzi.dev.coworking.Controller.JSON.Responses;

public class ErrorJSONResponse implements JSONResponse {
    private boolean successful;
    private int statusCode;
    private String message;
    private String timestamp;

    public ErrorJSONResponse setSuccessful(boolean successful) {
        this.successful = successful;
        return this;
    }

    public ErrorJSONResponse setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public ErrorJSONResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public ErrorJSONResponse setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
