package com.example.opbook.repository;

import com.example.opbook.model.CourseRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRatingRepository extends CrudRepository<CourseRating, Long> {
}
