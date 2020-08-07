package com.example.opbook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Email already taken")
public class EmailTakenException extends ConflictException {
    public EmailTakenException(String message) {
        super(message);
    }
}
