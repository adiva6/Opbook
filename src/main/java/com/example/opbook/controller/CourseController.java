package com.example.opbook.controller;

import com.example.opbook.exceptions.CourseNotFoundException;
import com.example.opbook.model.Course;
import com.example.opbook.model.Post;
import com.example.opbook.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CourseController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/courses")
    public Iterable<Course> getAllCourses() {
        return courseService.findAll();
    }

    @GetMapping(value = "/courses/{courseSymbol}")
    public ResponseEntity<Course> getCourse(@PathVariable(value = "courseSymbol") String courseSymbol) {
        Course course = courseService.findByCourseSymbol(courseSymbol);
        if (course == null) {
            String errorMessage = String.format("Course (%s) wasn't found!", courseSymbol);
            logger.error(errorMessage);
            throw new CourseNotFoundException(errorMessage);
        }

        return ResponseEntity.ok(course);
    }

    @GetMapping(value = "/courses/{courseSymbol}/posts")
    public ResponseEntity<Iterable<Post>> getCoursePosts(@PathVariable(value = "courseSymbol") String courseSymbol) {
        Course course = courseService.findByCourseSymbol(courseSymbol);
        if (course == null) {
            String errorMessage = String.format("Course (%s) wasn't found!", courseSymbol);
            logger.error(errorMessage);
            throw new CourseNotFoundException(errorMessage);
        }

        return ResponseEntity.ok(course.getPosts());
    }
}
