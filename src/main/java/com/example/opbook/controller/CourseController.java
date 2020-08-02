package com.example.opbook.controller;

import com.example.opbook.exceptions.CourseNotFoundException;
import com.example.opbook.model.Course;
import com.example.opbook.model.Post;
import com.example.opbook.repository.CourseRepository;
import com.example.opbook.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class CourseController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/courses")
    public Iterable<Course> getAllCourses() {
        return courseService.findAll();
    }

    @GetMapping(value = "/courses/{courseId}/posts")
    public ResponseEntity<Iterable<Post>> getCoursePosts(@PathVariable(value = "courseId") long courseId) {
        Optional<Course> course = courseService.findById(courseId);
        if (!course.isPresent()) {
            String errorMessage = String.format("Course #%d wasn't found!", courseId);
            logger.error(errorMessage);
            throw new CourseNotFoundException(errorMessage);
        }

        return ResponseEntity.ok(course.get().getPosts());
    }
}
