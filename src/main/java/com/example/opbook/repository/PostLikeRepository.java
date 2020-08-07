package com.example.opbook.repository;

import com.example.opbook.model.PostLike;
import com.example.opbook.model.PostLikeId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends CrudRepository<PostLike, PostLikeId> {
}
