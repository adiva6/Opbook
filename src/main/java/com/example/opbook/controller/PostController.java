package com.example.opbook.controller;

import com.example.opbook.exceptions.PostNotFoundException;
import com.example.opbook.model.Post;
import com.example.opbook.model.PostComment;
import com.example.opbook.service.PostCommentService;
import com.example.opbook.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PostController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PostService postService;

    @Autowired
    private PostCommentService postCommentService;

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

    @PostMapping(value = "/posts/{postId}/comments")
    public ResponseEntity<PostComment> submitPostComment(@PathVariable(value = "postId") long postId,
                                                         @Valid @RequestBody PostComment postComment) {
        Optional<Post> post = postService.findById(postId);
        if (!post.isPresent()) {
            String errorMessage = String.format("Post #%d wasn't found!", postId);
            logger.error(errorMessage);
            throw new PostNotFoundException(errorMessage);
        }

        postComment.setPost(post.get());
        postCommentService.save(postComment);
        logger.info("Comment was successfully submitted");
        return ResponseEntity.ok(postComment);
    }
}
