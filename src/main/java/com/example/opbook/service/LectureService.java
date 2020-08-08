package com.example.opbook.service;

import com.example.opbook.model.Lecture;
import com.example.opbook.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("lectureService")
public class LectureService {
    @Autowired
    private LectureRepository lectureRepository;

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public Optional<Lecture> findById(Long lectureId) {
        return this.lectureRepository.findById(lectureId);
    }

    public void save(Lecture lecture) {
        this.lectureRepository.save(lecture);
    }
}
