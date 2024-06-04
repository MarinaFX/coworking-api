package com.pazzi.dev.coworking.Controller;

import com.pazzi.dev.coworking.Controller.JSON.Factories.ConcreteErrorJSONResponseFactory;
import com.pazzi.dev.coworking.Controller.JSON.Factories.ConcreteSuccessfulJSONResponseFactory;
import com.pazzi.dev.coworking.Controller.JSON.Responses.ErrorJSONResponse;
import com.pazzi.dev.coworking.Controller.JSON.Responses.JSONResponse;
import com.pazzi.dev.coworking.Controller.JSON.Responses.SuccessfulJSONResponse;
import com.pazzi.dev.coworking.Exceptions.CoworkingNotFoundException;
import com.pazzi.dev.coworking.Model.WorkingPlace;
import com.pazzi.dev.coworking.Service.CoworkingService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.TransactionRequiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1")
public class CoworkingController {

    private final CoworkingService service;
    private final ConcreteSuccessfulJSONResponseFactory successfulJSONFactory;
    private final ConcreteErrorJSONResponseFactory errorJSONFactory;

    @Autowired
    public CoworkingController(CoworkingService service,
                               ConcreteSuccessfulJSONResponseFactory successfulJSONFactory,
                               ConcreteErrorJSONResponseFactory errorJSONFactory) {
        this.service = service;
        this.successfulJSONFactory = successfulJSONFactory;
        this.errorJSONFactory = errorJSONFactory;
    }

    //GET Mappings

    @GetMapping(value = "/")
    public String getTest() {
        return "Hello World";
    }

    @GetMapping("/coworking/{id}")
    public ResponseEntity<JSONResponse> getPlace(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            WorkingPlace result = service.fetchBy(uuid);

            SuccessfulJSONResponse response = successfulJSONFactory.create().getResponse();

            response = successfulJSONFactory.setData(result)
                    .setTimestamp(Date.from(Instant.now()).toString())
                    .getResponse();

            return new ResponseEntity<>(response, this.createJsonHeaders(), HttpStatus.OK);
        } catch (CoworkingNotFoundException f) {
            ErrorJSONResponse response = errorJSONFactory.create().getResponse();

            response = errorJSONFactory
                    .setStatusCode(HttpStatus.NOT_FOUND.value())
                    .setSuccessful(false)
                    .setMessage("There was no coworking found with the given ID")
                    .setTimestamp(Date.from(Instant.now()).toString())
                    .getResponse();

            return new ResponseEntity<>(response, this.createJsonHeaders(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            ErrorJSONResponse response = errorJSONFactory.create().getResponse();

            response = errorJSONFactory
                    .setStatusCode(HttpStatus.BAD_REQUEST.value())
                    .setSuccessful(false)
                    .setMessage("The ID provided is not valid. Please, make sure its an UUID")
                    .setTimestamp(Date.from(Instant.now()).toString())
                    .getResponse();

            return new ResponseEntity<>(response, this.createJsonHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping(value = "/getAll")
//    public ResponseEntity<JSONResponse<List<WorkingPlace>>> getAll() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/json");
//
//        List<WorkingPlace> result = service.getAllPlaces();
//
//        JSONResponse<List<WorkingPlace>> response = new JSONResponse<>();
//
//        if(!result.isEmpty()) {
//
//            response.setData(result);
//
//            return new ResponseEntity<>(response, headers, HttpStatus.OK);
//        }
//        else {
//            response.setMessage(null);
//
//            return new ResponseEntity<>(response, headers, HttpStatus.OK);
//        }
//    }

    // POST Mappings


    @PostMapping("/coworking")
    public ResponseEntity<JSONResponse> saveCoworking(@RequestBody WorkingPlace coworking) {
        try {
            this.service.saveCoworking(coworking);

            SuccessfulJSONResponse response = successfulJSONFactory.create().getResponse();

            response = successfulJSONFactory.setData(coworking)
                    .setTimestamp(Date.from(Instant.now()).toString())
                    .getResponse();

            return new ResponseEntity<>(response, this.createJsonHeaders(), HttpStatus.OK);
        } catch (EntityExistsException e) {
            ErrorJSONResponse response = errorJSONFactory.create().getResponse();

            response = errorJSONFactory
                    .setStatusCode(HttpStatus.CONFLICT.value())
                    .setSuccessful(false)
                    .setMessage("Coworking not saved because it already exists in the database.")
                    .setTimestamp(Date.from(Instant.now()).toString())
                    .getResponse();

            return new ResponseEntity<>(response, this.createJsonHeaders(), HttpStatus.CONFLICT);
        } catch (IllegalArgumentException f) {
            ErrorJSONResponse response = errorJSONFactory.create().getResponse();

            response = errorJSONFactory
                    .setStatusCode(HttpStatus.BAD_REQUEST.value())
                    .setSuccessful(false)
                    .setMessage("Coworking not saved because the given body does not match a valid coworking.")
                    .setTimestamp(Date.from(Instant.now()).toString())
                    .getResponse();

            return new ResponseEntity<>(response, this.createJsonHeaders(), HttpStatus.BAD_REQUEST);
        } catch (TransactionRequiredException r) {
            ErrorJSONResponse response = errorJSONFactory.create().getResponse();

            response = errorJSONFactory
                    .setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .setSuccessful(false)
                    .setMessage("Coworking not saved because no transaction was given.")
                    .setTimestamp(Date.from(Instant.now()).toString())
                    .getResponse();

            return new ResponseEntity<>(response, this.createJsonHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RuntimeException t) {
            ErrorJSONResponse response = errorJSONFactory.create().getResponse();

            response = errorJSONFactory
                    .setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .setSuccessful(false)
                    .setMessage("flemis")
                    .setTimestamp(Date.from(Instant.now()).toString())
                    .getResponse();

            return new ResponseEntity<>(response, this.createJsonHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private HttpHeaders createJsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return headers;
    }
}
