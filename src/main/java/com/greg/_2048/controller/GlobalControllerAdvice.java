package com.greg._2048.controller;

import com.greg._2048.exception.BadRequestException;
import com.greg._2048.exception.InternalServerError;
import com.greg._2048.exception.NotFoundException;
import com.greg._2048.model.GameError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<GameError> handle(BadRequestException ex) {
        return ResponseEntity.badRequest().
                body(newError(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
    
    @ExceptionHandler
    public ResponseEntity<GameError> handle(InternalServerError ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(newError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
    
    @ExceptionHandler
    public ResponseEntity<GameError> handle(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(newError(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }
    
    private GameError newError(String message, Integer code) {
        var error = new GameError();
        error.setMessage(message);
        error.setCode(String.valueOf(code));
        
        return error;
    }
}
