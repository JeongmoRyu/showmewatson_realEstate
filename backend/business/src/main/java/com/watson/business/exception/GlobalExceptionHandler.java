package com.watson.business.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HouseException.class)
    public ResponseEntity<String> handleHouseException(HouseException ex) {
        return ResponseEntity.status(ex.getCode()).body(ex.getMessage());
    }

}
