package com.example.opbook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Course wasn't found")
public class CourseNotFoundException extends NotFoundException {
    public CourseNotFoundException(String message) {
        super(message);
    }
}
