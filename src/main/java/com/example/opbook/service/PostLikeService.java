package com.example.opbook.service;

import com.example.opbook.model.PostLike;
import com.example.opbook.model.PostLikeId;
import com.example.opbook.repository.PostLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("postLikeService")
public class PostLikeService {
    @Autowired
    private PostLikeRepository postLikeRepository;

    public PostLikeService(PostLikeRepository postLikeRepository) {
        this.postLikeRepository = postLikeRepository;
    }

    public Optional<PostLike> findById(Long userId, Long postId) {
        PostLikeId postLikeId = new PostLikeId(userId, postId);
        return this.postLikeRepository.findById(postLikeId);
    }

    public void save(PostLike postLike) {
        this.postLikeRepository.save(postLike);
    }

    public void delete(PostLike postLike) {
        this.postLikeRepository.delete(postLike);
    }
}
