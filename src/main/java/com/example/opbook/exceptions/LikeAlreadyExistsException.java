package com.example.opbook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Like already exists")
public class LikeAlreadyExistsException extends ConflictException {
    public LikeAlreadyExistsException(String message) {
        super(message);
    }
}
