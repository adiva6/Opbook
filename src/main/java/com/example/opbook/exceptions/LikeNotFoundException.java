package com.example.opbook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Like wasn't found")
public class LikeNotFoundException extends NotFoundException {
    public LikeNotFoundException(String message) {
        super(message);
    }
}
