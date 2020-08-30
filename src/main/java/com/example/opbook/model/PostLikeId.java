package com.example.opbook.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PostLikeId implements Serializable {
    private Long userId;

    private Long postId;

    public PostLikeId() {

    }

    public PostLikeId(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getPostId() {
        return postId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, postId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PostLikeId other = (PostLikeId) obj;
        return Objects.equals(userId, other.getUserId())
                && Objects.equals(postId, other.getPostId());
    }
}
