package com.example.opbook.controller;

import com.example.opbook.exceptions.LectureNotFoundException;
import com.example.opbook.model.Course;
import com.example.opbook.model.Lecture;
import com.example.opbook.model.LectureComment;
import com.example.opbook.model.User;
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
public class LectureController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(LectureController.class);

    @Autowired
    private LectureService lectureService;

    @Autowired
    private LectureCommentService lectureCommentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseUtils courseUtils;

    @PreAuthorize("isEnrolledStudent(#courseSymbol)")
    @GetMapping(value = "/courses/{courseSymbol}/lectures")
    public ResponseEntity<Iterable<Lecture>> getCourseLectures(
            @PathVariable(value = "courseSymbol") String courseSymbol) {
        Course course = courseUtils.findCourseBySymbol(courseSymbol);
        return ResponseEntity.ok(course.getLectures());
    }

    @PostMapping(value = "/lectures/{lectureId}/comments")
    public ResponseEntity<LectureComment> submitLectureComment(@PathVariable(value = "lectureId") long lectureId,
                                                               @Valid @RequestBody LectureComment lectureComment,
                                                               Principal principal) {
        Lecture lecture = findLectureById(lectureId);
        User submitter = userService.findByEmail(principal.getName());

        lectureComment.setLecture(lecture);
        lectureComment.setUser(submitter);

        lectureCommentService.save(lectureComment);
        logger.info("Comment was successfully submitted");
        return ResponseEntity.ok(lectureComment);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/courses/{courseSymbol}/lectures")
    public ResponseEntity<Lecture> uploadLecture(@PathVariable(value = "courseSymbol") String courseSymbol,
                                                 @Valid @RequestBody Lecture lecture) {
        Course course = courseUtils.findCourseBySymbol(courseSymbol);

        lecture.setCourse(course);
        lectureService.save(lecture);
        logger.info("Lecture was uploaded successfully");
        return ResponseEntity.ok(lecture);
    }

    private Lecture findLectureById(long lectureId) {
        Optional<Lecture> lecture = this.lectureService.findById(lectureId);
        if (!lecture.isPresent()) {
            String errorMessage = String.format("Lecture #%d wasn't found!", lectureId);
            logger.error(errorMessage);
            throw new LectureNotFoundException(errorMessage);
        }

        return lecture.get();
    }
}
