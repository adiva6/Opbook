package com.example.opbook.controller;

import com.example.opbook.exceptions.*;
import com.example.opbook.model.*;
import com.example.opbook.restutils.CourseUtils;
import com.example.opbook.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;


@RestController
public class CourseController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseRatingService courseRatingService;

    @Autowired
    private CourseUtils courseUtils;

    @Autowired
    private UserCourseService userCourseService;

    @GetMapping(value = "/courses")
    public Iterable<Course> getAllCourses() {
        return courseService.findAll();
    }

    @GetMapping(value = "/courses/{courseSymbol}")
    public ResponseEntity<Course> getCourse(@PathVariable(value = "courseSymbol") String courseSymbol) {
        Course course = courseUtils.findCourseBySymbol(courseSymbol);
        return ResponseEntity.ok(course);
    }

    @PreAuthorize("isEnrolledStudent(#courseSymbol)")
    @PostMapping(value = "/courses/{courseSymbol}/ratings")
    public ResponseEntity<CourseRating> submitCourseRating(@PathVariable(value = "courseSymbol") String courseSymbol,
                                                           @Valid @RequestBody CourseRating courseRating,
                                                           Principal principal) {
        Course course = courseUtils.findCourseBySymbol(courseSymbol);
        User submitter = userService.findByEmail(principal.getName());
        CourseRating existingCourseRating = courseRatingService.findByCourseAndUser(course, submitter);

        if (existingCourseRating != null) {
            String errorMessage = String.format("User %s already rated course %s!",
                    submitter.getName(), course.getCourseSymbol());
            logger.error(errorMessage);
            throw new AlreadyRatedException(errorMessage);
        }

        courseRating.setCourse(course);
        courseRating.setUser(submitter);
        this.courseRatingService.save(courseRating);
        logger.info("Course rating was successfully submitted");
        return ResponseEntity.ok(courseRating);
    }

    @PostMapping(value = "/courses/{courseSymbol}/students")
    public ResponseEntity<User> joinCourse(@PathVariable(value = "courseSymbol") String courseSymbol,
                                           Principal principal) {
        Course course = courseUtils.findCourseBySymbol(courseSymbol);
        User student = userService.findByEmail(principal.getName());

        Optional<UserCourse> userCourse = userCourseService.findById(student.getId(), course.getId());
        if (userCourse.isPresent()) {
            String errorMessage = String.format("User #%d is already enrolled to course %s!",
                    student.getId(), courseSymbol);
            logger.error(errorMessage);
            throw new UserAlreadyEnrolledException(errorMessage);
        }

        UserCourse newUserCourse = new UserCourse(student, course);
        userCourseService.save(newUserCourse);
        logger.info("User was successfully enrolled");
        return ResponseEntity.ok(student);
    }

    @DeleteMapping(value = "/courses/{courseSymbol}/students")
    public ResponseEntity<User> leaveCourse(@PathVariable(value = "courseSymbol") String courseSymbol,
                                            Principal principal) {
        Course course = courseUtils.findCourseBySymbol(courseSymbol);
        User student = userService.findByEmail(principal.getName());

        Optional<UserCourse> userCourse = userCourseService.findById(student.getId(), course.getId());
        if (!userCourse.isPresent()) {
            String errorMessage = String.format("User #%d isn't enrolled to course %s!",
                    student.getId(), courseSymbol);
            logger.error(errorMessage);
            throw new UserCourseNotFoundException(errorMessage);
        }

        userCourseService.delete(userCourse.get());
        logger.info("User was successfully removed from course");
        return ResponseEntity.ok(student);
    }

}
