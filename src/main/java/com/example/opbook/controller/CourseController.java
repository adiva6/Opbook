package com.example.opbook.controller;

import com.example.opbook.model.Course;
import com.example.opbook.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(value="/courses")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
