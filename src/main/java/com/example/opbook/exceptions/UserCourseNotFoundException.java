package com.example.opbook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User isn't enrolled to course")
public class UserCourseNotFoundException extends NotFoundException {
    public UserCourseNotFoundException(String message) {
        super(message);
    }
}
