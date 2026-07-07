package com.greg._2048.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerError extends RuntimeException {
    
    public InternalServerError(String message, Throwable cause) {
        super(message, cause);
    }
    
    public InternalServerError(String message) {
        super(message);
    }
    
}
