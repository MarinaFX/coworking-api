package com.pazzi.dev.coworking.Controller.JSON.Factories;

import com.pazzi.dev.coworking.Controller.JSON.Responses.JSONResponse;
import com.pazzi.dev.coworking.Controller.JSON.Responses.SuccessfulJSONResponse;
import org.springframework.stereotype.Component;

@Component
public class ConcreteSuccessfulJSONResponseFactory<T> implements SuccessfulJSONResponseFactory<T> {

    private SuccessfulJSONResponse<T> response;

    @Override
    public ConcreteSuccessfulJSONResponseFactory<T> create() {
        this.response = new SuccessfulJSONResponse<>();
        return this;
    }

    @Override
    public SuccessfulJSONResponse<T> getResponse() {
        return this.response;
    }

    @Override
    public ConcreteSuccessfulJSONResponseFactory<T> setData(T data) {
        this.response.setData(data);
        return this;
    }

    @Override
    public ConcreteSuccessfulJSONResponseFactory<T> setTimestamp(String timestamp) {
        this.response.setTimestamp(timestamp);
        return this;
    }
}
