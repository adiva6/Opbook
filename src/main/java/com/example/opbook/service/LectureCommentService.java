package com.example.opbook.service;

import com.example.opbook.model.LectureComment;
import com.example.opbook.repository.LectureCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("lectureCommentService")
public class LectureCommentService {
    @Autowired
    private LectureCommentRepository lectureCommentRepository;

    public LectureCommentService(LectureCommentRepository lectureCommentRepository) {
        this.lectureCommentRepository = lectureCommentRepository;
    }

    public void save(LectureComment lectureComment) {
        this.lectureCommentRepository.save(lectureComment);
    }
}
