package com.example.opbook.repository;

import com.example.opbook.model.LectureComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureCommentRepository extends CrudRepository<LectureComment, Long> {
}
