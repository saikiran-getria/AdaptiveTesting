package com.ria.adaptiveTesting.exception;

import org.springframework.http.HttpStatus;

public class AcmeAppException extends RuntimeException {
    public final String message;
    public final HttpStatus status;
    public AcmeAppException(String message, HttpStatus status) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
