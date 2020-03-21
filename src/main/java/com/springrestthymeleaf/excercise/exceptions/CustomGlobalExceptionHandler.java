package com.springrestthymeleaf.excercise.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({MemberNotFoundException.class, UserNotFoundException.class, InvalidIdException.class})
    public ResponseEntity<?> springHandleNotFound(CustomException ce) {
        return new ResponseEntity<>(ce.getHttpStatus());
    }
}
