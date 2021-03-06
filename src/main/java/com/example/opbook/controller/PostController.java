package com.example.opbook.controller;

import com.example.opbook.exceptions.LikeAlreadyExistsException;
import com.example.opbook.exceptions.LikeNotFoundException;
import com.example.opbook.exceptions.PostNotFoundException;
import com.example.opbook.model.*;
import com.example.opbook.restutils.CourseUtils;
import com.example.opbook.service.PostCommentService;
import com.example.opbook.service.PostLikeService;
import com.example.opbook.service.PostService;
import com.example.opbook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@RestController
public class PostController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    @Autowired
    private PostCommentService postCommentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostLikeService postLikeService;

    @Autowired
    private CourseUtils courseUtils;

    @PreAuthorize("isEnrolledStudent(#courseSymbol)")
    @PostMapping(value = "/courses/{courseSymbol}/posts")
    public ResponseEntity<Post> submitPost(@PathVariable(value = "courseSymbol") String courseSymbol,
                                           @Valid @RequestBody Post post,
                                           Principal principal) {
        Course course = courseUtils.findCourseBySymbol(courseSymbol);
        User submitter = userService.findByEmail(principal.getName());

        post.setSubmitter(submitter);
        post.setCourse(course);

        this.postService.save(post);
        logger.info("Post was successfully submitted");
        return ResponseEntity.ok(post);
    }

    @PreAuthorize("isEnrolledStudent(#courseSymbol)")
    @PostMapping(value = "/courses/{courseSymbol}/posts/{postId}/comments")
    public ResponseEntity<PostComment> submitPostComment(@PathVariable(value = "courseSymbol") String courseSymbol,
                                                         @PathVariable(value = "postId") long postId,
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

    @PreAuthorize("isEnrolledStudent(#courseSymbol)")
    @PostMapping(value = "/courses/{courseSymbol}/posts/{postId}/likes")
    public ResponseEntity<User> submitPostLike(@PathVariable(value = "courseSymbol") String courseSymbol,
                                               @PathVariable(value = "postId") long postId,
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

    @PreAuthorize("isEnrolledStudent(#courseSymbol)")
    @DeleteMapping(value = "/courses/{courseSymbol}/posts/{postId}/likes")
    public ResponseEntity<User> deletePostLike(@PathVariable(value = "courseSymbol") String courseSymbol,
                                               @PathVariable(value = "postId") long postId,
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

    @PreAuthorize("isEnrolledStudent(#courseSymbol)")
    @GetMapping(value = "/courses/{courseSymbol}/posts")
    public ResponseEntity<Iterable<Post>> getCoursePosts(@PathVariable(value = "courseSymbol") String courseSymbol) {
        Course course = courseUtils.findCourseBySymbol(courseSymbol);
        return ResponseEntity.ok(course.getPosts());
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
