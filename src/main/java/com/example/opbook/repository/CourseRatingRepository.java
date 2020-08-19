package com.example.opbook.repository;

import com.example.opbook.model.Course;
import com.example.opbook.model.CourseRating;
import com.example.opbook.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRatingRepository extends CrudRepository<CourseRating, Long> {
    public CourseRating findByCourseAndUser(Course course, User user);
}
