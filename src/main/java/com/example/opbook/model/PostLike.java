package com.example.opbook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "PostLike")
public class PostLike {
    public PostLike() {

    }

    public PostLike(User user, Post post) {
        this.user = user;
        this.post = post;
        this.postLikeId = new PostLikeId(user.getId(), post.getId());
    }

    @EmbeddedId
    @JsonIgnore
    private PostLikeId postLikeId;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false, insertable = false, updatable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "PostID", nullable = false, insertable = false, updatable = false)
    private Post post;
}
