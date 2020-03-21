package com.springrestthymeleaf.excercise.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({MemberNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<?> springHandleNotFound() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
