package com.example.opbook.controller;

import com.example.opbook.exceptions.EmailTakenException;
import com.example.opbook.model.User;
import com.example.opbook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class UserController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = "/students")
    public Iterable<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        // Lookup user in database by e-mail
        User userExists = userService.findByEmail(user.getEmail());

        if (userExists != null) {
            logger.error(String.format("User with e-mail %s is already registered!", user.getEmail()));
            throw new EmailTakenException();
        }

        userService.save(user);
        return ResponseEntity.ok(user);
    }
}
