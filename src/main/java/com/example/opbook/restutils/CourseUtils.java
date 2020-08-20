package com.example.opbook.restutils;

import com.example.opbook.exceptions.CourseNotFoundException;
import com.example.opbook.model.Course;
import com.example.opbook.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseUtils {
    private static final Logger logger = LoggerFactory.getLogger(CourseUtils.class);;

    @Autowired
    private CourseService courseService;

    public Course findCourseBySymbol(String courseSymbol) {
        Course course = courseService.findByCourseSymbol(courseSymbol);
        if (course == null) {
            String errorMessage = String.format("Course (%s) wasn't found!", courseSymbol);
            logger.error(errorMessage);
            throw new CourseNotFoundException(errorMessage);
        }

        return course;
    }
}
