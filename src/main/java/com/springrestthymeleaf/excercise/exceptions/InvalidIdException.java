package com.springrestthymeleaf.excercise.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidIdException extends CustomException {

    public InvalidIdException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
