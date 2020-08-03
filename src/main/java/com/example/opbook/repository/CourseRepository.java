package com.example.opbook.repository;

import com.example.opbook.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    public Course findByCourseSymbol(String courseSymbol);
}
