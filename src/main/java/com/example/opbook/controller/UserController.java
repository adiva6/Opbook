package com.example.opbook.controller;

import com.example.opbook.model.User;
import com.example.opbook.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value="/students")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
