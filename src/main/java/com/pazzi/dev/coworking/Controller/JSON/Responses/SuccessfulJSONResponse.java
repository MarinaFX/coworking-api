package com.pazzi.dev.coworking.Controller.JSON.Responses;


public class SuccessfulJSONResponse<T> implements JSONResponse {
    private T data;
    private String timestamp;

    public SuccessfulJSONResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public SuccessfulJSONResponse<T> setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public T getData() {
        return data;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
