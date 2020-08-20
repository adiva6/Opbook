package com.example.opbook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User already enrolled to course")
public class UserAlreadyEnrolledException extends ConflictException {
    public UserAlreadyEnrolledException(String message) {
        super(message);
    }
}
