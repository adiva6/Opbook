package com.example.opbook.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PostLikeId implements Serializable {
    private Integer userId;

    private Integer postId;

    public PostLikeId() {

    }

    public PostLikeId(Long userId, Long postId) {
        this.userId = userId.intValue();
        this.postId = postId.intValue();
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getPostId() {
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
