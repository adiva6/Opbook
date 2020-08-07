package com.example.opbook.service;

import com.example.opbook.model.Post;
import com.example.opbook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("postService")
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    public void save(Post post) {
        this.postRepository.save(post);
    }
}
