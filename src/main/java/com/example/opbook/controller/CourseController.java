package com.example.opbook.controller;

import com.example.opbook.exceptions.CourseNotFoundException;
import com.example.opbook.model.*;
import com.example.opbook.service.CourseService;
import com.example.opbook.service.PostService;
import com.example.opbook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


@RestController
public class CourseController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/courses")
    public Iterable<Course> getAllCourses() {
        return courseService.findAll();
    }

    @GetMapping(value = "/courses/{courseSymbol}")
    public ResponseEntity<Course> getCourse(@PathVariable(value = "courseSymbol") String courseSymbol) {
        Course course = findCourseBySymbol(courseSymbol);
        return ResponseEntity.ok(course);
    }

    @GetMapping(value = "/courses/{courseSymbol}/lectures")
    public ResponseEntity<Iterable<Lecture>> getCourseLectures(
            @PathVariable(value = "courseSymbol") String courseSymbol) {
        Course course = findCourseBySymbol(courseSymbol);
        return ResponseEntity.ok(course.getLectures());
    }

    @PostMapping(value = "/courses/{courseSymbol}/posts")
    public ResponseEntity<Post> submitPost(@PathVariable(value = "courseSymbol") String courseSymbol,
                                           @Valid @RequestBody Post post,
                                           Principal principal) {
        Course course = findCourseBySymbol(courseSymbol);
        User submitter = userService.findByEmail(principal.getName());

        post.setSubmitter(submitter);
        post.setCourse(course);

        this.postService.save(post);
        logger.info("Post was successfully submitted");
        return ResponseEntity.ok(post);
    }

    @GetMapping(value = "/courses/{courseSymbol}/posts")
    public ResponseEntity<Iterable<Post>> getCoursePosts(@PathVariable(value = "courseSymbol") String courseSymbol) {
        Course course = findCourseBySymbol(courseSymbol);
        return ResponseEntity.ok(course.getPosts());
    }

    private Course findCourseBySymbol(String courseSymbol) {
        Course course = courseService.findByCourseSymbol(courseSymbol);
        if (course == null) {
            String errorMessage = String.format("Course (%s) wasn't found!", courseSymbol);
            logger.error(errorMessage);
            throw new CourseNotFoundException(errorMessage);
        }

        return course;
    }
}
