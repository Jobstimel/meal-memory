package com.mealmemory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.ConnectException;

/**
 * @author Christian Jobst
 * created: 25.02.2023
 */
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = { ConnectException.class })
    public ResponseEntity<Object> handleConnectException(ConnectException e, WebRequest request) {
        return new ResponseEntity<>("failed to connect to database", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { APIPreconditionFailedException.class })
    public ResponseEntity<Object> handleAPIPreconditionFailedException(APIPreconditionFailedException e, WebRequest request) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
    }

}
