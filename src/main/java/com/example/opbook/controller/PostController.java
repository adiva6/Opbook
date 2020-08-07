package com.example.opbook.controller;

import com.example.opbook.exceptions.LikeAlreadyExistsException;
import com.example.opbook.exceptions.LikeNotFoundException;
import com.example.opbook.exceptions.PostNotFoundException;
import com.example.opbook.model.Post;
import com.example.opbook.model.PostComment;
import com.example.opbook.model.PostLike;
import com.example.opbook.model.User;
import com.example.opbook.service.PostCommentService;
import com.example.opbook.service.PostLikeService;
import com.example.opbook.service.PostService;
import com.example.opbook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@RestController
public class PostController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PostService postService;

    @Autowired
    private PostCommentService postCommentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostLikeService postLikeService;

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
                                                         @Valid @RequestBody PostComment postComment,
                                                         Principal principal) {
        Post post = findPostById(postId);
        User submitter = userService.findByEmail(principal.getName());

        postComment.setPost(post);
        postComment.setUser(submitter);

        postCommentService.save(postComment);
        logger.info("Comment was successfully submitted");
        return ResponseEntity.ok(postComment);
    }

    @PostMapping(value = "/posts/{postId}/likes")
    public ResponseEntity<User> submitPostLike(@PathVariable(value = "postId") long postId,
                                               Principal principal) {
        Post post = findPostById(postId);
        User submitter = userService.findByEmail(principal.getName());

        Optional<PostLike> postLike = postLikeService.findById(submitter.getId(), postId);
        if (postLike.isPresent()) {
            String errorMessage = String.format("User #%d already likes post #%d!", submitter.getId(), post.getId());
            logger.error(errorMessage);
            throw new LikeAlreadyExistsException(errorMessage);
        }

        PostLike newPostLike = new PostLike(submitter, post);
        postLikeService.save(newPostLike);
        logger.info("Like was successfully submitted");
        return ResponseEntity.ok(submitter);
    }

    @DeleteMapping(value = "/posts/{postId}/likes")
    public ResponseEntity<User> deletePostLike(@PathVariable(value = "postId") long postId,
                                               Principal principal) {
        Post post = findPostById(postId);
        User submitter = userService.findByEmail(principal.getName());

        Optional<PostLike> postLike = postLikeService.findById(submitter.getId(), postId);
        if (!postLike.isPresent()) {
            String errorMessage = String.format("User #%d doesn't like post #%d!", submitter.getId(), post.getId());
            logger.error(errorMessage);
            throw new LikeNotFoundException(errorMessage);
        }

        postLikeService.delete(postLike.get());
        logger.info("Like was successfully deleted");
        return ResponseEntity.ok(submitter);
    }

    private Post findPostById(long postId) {
        Optional<Post> post = postService.findById(postId);
        if (!post.isPresent()) {
            String errorMessage = String.format("Post #%d wasn't found!", postId);
            logger.error(errorMessage);
            throw new PostNotFoundException(errorMessage);
        }

        return post.get();
    }
}
