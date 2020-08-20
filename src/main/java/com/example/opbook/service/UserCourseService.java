package com.example.opbook.service;

import com.example.opbook.model.PostLike;
import com.example.opbook.model.PostLikeId;
import com.example.opbook.model.UserCourse;
import com.example.opbook.model.UserCourseId;
import com.example.opbook.repository.UserCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userCourseService")
public class UserCourseService {
    @Autowired
    private UserCourseRepository userCourseRepository;

    public UserCourseService(UserCourseRepository userCourseRepository) {
        this.userCourseRepository = userCourseRepository;
    }

    public Optional<UserCourse> findById(Long userId, Long courseId) {
        UserCourseId userCourseId = new UserCourseId(userId, courseId);
        return this.userCourseRepository.findById(userCourseId);
    }

    public void save(UserCourse userCourse) {
        this.userCourseRepository.save(userCourse);
    }

    public void delete(UserCourse userCourse) {
        this.userCourseRepository.delete(userCourse);
    }
}
