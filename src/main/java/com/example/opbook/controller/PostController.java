package com.example.opbook.controller;

import com.example.opbook.exceptions.PostNotFoundException;
import com.example.opbook.model.Post;
import com.example.opbook.model.PostComment;
import com.example.opbook.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PostService postService;

    @GetMapping(value = "/posts/{postId}/comments")
    public ResponseEntity<Iterable<PostComment>> getPostComments(@PathVariable(value = "postId") long postId) {
        Optional<Post> post = postService.findById(postId);
        if (!post.isPresent()) {
            String errorMessage = String.format("Post #%d wasn't found!", postId);
            logger.error(errorMessage);
            throw new PostNotFoundException(errorMessage);
        }

        return ResponseEntity.ok(post.get().getComments());
    }
}
