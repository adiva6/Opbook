package com.example.opbook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User already rated course")
public class AlreadyRatedException extends ConflictException {
    public AlreadyRatedException(String message) {
        super(message);
    }
}
