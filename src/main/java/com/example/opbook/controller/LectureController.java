package com.example.opbook.controller;

import com.example.opbook.exceptions.LectureNotFoundException;
import com.example.opbook.model.Lecture;
import com.example.opbook.model.LectureComment;
import com.example.opbook.model.User;
import com.example.opbook.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
