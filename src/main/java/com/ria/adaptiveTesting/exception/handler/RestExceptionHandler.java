package com.ria.adaptiveTesting.exception.handler;

import com.ria.adaptiveTesting.exception.AcmeAppException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AcmeAppException.class)
    public ResponseEntity<Object> handleBirdAppException(AcmeAppException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorMessage(Instant.now(), ex.getMessage()), ex.getStatus());
    }

}