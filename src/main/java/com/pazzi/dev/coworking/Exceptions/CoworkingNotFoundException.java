package com.pazzi.dev.coworking.Exceptions;

public class CoworkingNotFoundException extends RuntimeException {
    public CoworkingNotFoundException() {
        super();
    }

    public CoworkingNotFoundException(String message) {
        super(message);
    }

    public CoworkingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoworkingNotFoundException(Throwable cause) {
        super(cause);
    }
}
