package com.mealmemory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Christian Jobst
 * created: 25.02.2023
 */
@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class APIPreconditionFailedException extends Exception {

    public APIPreconditionFailedException(String msg) {
        super(msg);
    }

}
