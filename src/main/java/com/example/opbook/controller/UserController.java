package com.example.opbook.controller;

import com.example.opbook.exceptions.EmailTakenException;
import com.example.opbook.model.User;
import com.example.opbook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


@RestController
public class UserController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = "/students")
    public Iterable<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping(value = "/user")
    public ResponseEntity<User> getUser(Principal principal) {
        return ResponseEntity.ok(userService.findByEmail(principal.getName()));
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        // Lookup user in database by e-mail
        User userExists = userService.findByEmail(user.getEmail());

        if (userExists != null) {
            String errorMessage = String.format("User with e-mail %s is already registered!", user.getEmail());
            logger.error(errorMessage);
            throw new EmailTakenException(errorMessage);
        }

        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailTakenException.class)
    public String handleValidationExceptions(EmailTakenException ex) {
        return ex.getMessage();
    }
}
