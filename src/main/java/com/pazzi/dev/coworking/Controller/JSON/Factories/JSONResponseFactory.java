package com.pazzi.dev.coworking.Controller.JSON.Factories;

import com.pazzi.dev.coworking.Controller.JSON.Responses.JSONResponse;

public interface JSONResponseFactory {

    public JSONResponseFactory create();

    public JSONResponse getResponse();
}
