package com.example.opbook.repository;

import com.example.opbook.model.PostComment;
import org.springframework.data.repository.CrudRepository;

public interface PostCommentRepository extends CrudRepository<PostComment, Long> {
}
