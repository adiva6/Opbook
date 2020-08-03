package com.example.opbook.service;

import com.example.opbook.model.Course;
import com.example.opbook.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("courseService")
public class CourseService {
    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Iterable<Course> findAll() {
        return courseRepository.findAll();
    }

    public Optional<Course> findById(long id) {
        return courseRepository.findById(id);
    }

    public Course findByCourseSymbol(String courseSymbol) {
        return courseRepository.findByCourseSymbol(courseSymbol);
    }
}
