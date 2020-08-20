package com.example.opbook.repository;

import com.example.opbook.model.UserCourse;
import com.example.opbook.model.UserCourseId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCourseRepository extends CrudRepository<UserCourse, UserCourseId> {
}
