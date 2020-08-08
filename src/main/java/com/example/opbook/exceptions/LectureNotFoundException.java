package com.example.opbook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Lecture wasn't found")
public class LectureNotFoundException extends NotFoundException {
    public LectureNotFoundException(String message) {
        super(message);
    }
}
