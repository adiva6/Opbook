package com.example.opbook.service;

import com.example.opbook.model.PostComment;
import com.example.opbook.repository.PostCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("postCommentService")
public class PostCommentService {
    private PostCommentRepository postCommentRepository;

    @Autowired
    public PostCommentService(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }

    public void save(PostComment postComment) {
        this.postCommentRepository.save(postComment);
    }
}
