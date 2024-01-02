package com.fidaamahboob.financeapp.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice

public class DataExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<Object> exception(DataNotFoundException exception){
        return new ResponseEntity<>("Error! Finance Record Not Found", HttpStatus.NOT_FOUND);
    } 
}
