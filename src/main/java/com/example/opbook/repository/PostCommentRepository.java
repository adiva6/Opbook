package com.example.opbook.repository;

import com.example.opbook.model.PostComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentRepository extends CrudRepository<PostComment, Long> {
}
