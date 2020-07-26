package com.example.opbook.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="PostComments")
public class PostComments {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="UserID", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="PostID", nullable=false)
    private Post post;

    private Date creationTime;

    private String content;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Post getPost() { return post; }

    public void setPost(Post post) { this.post = post; }

    public Date getCreationTime() { return creationTime; }

    public void setCreationTime(Date creationTime) { this.creationTime = creationTime; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }
}
