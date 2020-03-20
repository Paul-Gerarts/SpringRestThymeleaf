package com.springrestthymeleaf.excercise.exceptions;

import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends CustomException {

    public MemberNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
