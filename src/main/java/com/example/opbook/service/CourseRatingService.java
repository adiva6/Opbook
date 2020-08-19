package com.example.opbook.service;

import com.example.opbook.model.Course;
import com.example.opbook.model.CourseRating;
import com.example.opbook.model.User;
import com.example.opbook.repository.CourseRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("courseRatingService")
public class CourseRatingService {
    private CourseRatingRepository courseRatingRepository;

    @Autowired
    public CourseRatingService(CourseRatingRepository courseRatingRepository) {
        this.courseRatingRepository = courseRatingRepository;
    }

    public void save(CourseRating courseRating) {
        this.courseRatingRepository.save(courseRating);
    }

    public CourseRating findByCourseAndUser(Course course, User user) {
        return this.courseRatingRepository.findByCourseAndUser(course, user);
    }
}
