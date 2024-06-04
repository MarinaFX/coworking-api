package com.pazzi.dev.coworking.Controller.JSON.Factories;

import com.pazzi.dev.coworking.Controller.JSON.Responses.JSONResponse;
import org.springframework.stereotype.Component;

public interface ErrorJSONResponseFactory extends JSONResponseFactory {

    public ErrorJSONResponseFactory setStatusCode(int statusCode);

    public ErrorJSONResponseFactory setSuccessful(boolean successful);

    public ErrorJSONResponseFactory setMessage(String data);

    public ErrorJSONResponseFactory setTimestamp(String timestamp);
}
