package com.pazzi.dev.coworking.Controller.JSON.Factories;

import com.pazzi.dev.coworking.Controller.JSON.Responses.SuccessfulJSONResponse;
import org.springframework.stereotype.Component;

public interface SuccessfulJSONResponseFactory<T> extends JSONResponseFactory {

    public SuccessfulJSONResponseFactory<T> setData(T data);

    public SuccessfulJSONResponseFactory<T> setTimestamp(String timestamp);
}
