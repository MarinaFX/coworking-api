package com.pazzi.dev.coworking.Controller.JSON.Factories;

import com.pazzi.dev.coworking.Controller.JSON.Responses.ErrorJSONResponse;
import com.pazzi.dev.coworking.Controller.JSON.Responses.JSONResponse;
import org.springframework.stereotype.Component;

@Component
public class ConcreteErrorJSONResponseFactory implements ErrorJSONResponseFactory {

    private ErrorJSONResponse response;

    @Override
    public ConcreteErrorJSONResponseFactory setStatusCode(int statusCode) {
        this.response.setStatusCode(statusCode);
        return this;
    }

    @Override
    public ConcreteErrorJSONResponseFactory setSuccessful(boolean successful) {
        return this;
    }

    @Override
    public ConcreteErrorJSONResponseFactory setMessage(String message) {
        this.response.setMessage(message);
        return this;
    }

    @Override
    public ConcreteErrorJSONResponseFactory setTimestamp(String timestamp) {
        this.response.setTimestamp(timestamp);
        return this;
    }

    @Override
    public ConcreteErrorJSONResponseFactory create() {
        this.response = new ErrorJSONResponse();
        return this;
    }

    @Override
    public ErrorJSONResponse getResponse() {
        return this.response;
    }
}
