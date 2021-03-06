package com.example.opbook.model;


import com.example.opbook.parser.PostJsonDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@JsonDeserialize(using = PostJsonDeserializer.class)
@Entity
@Table(name = "Post")
public class Post {
    public Post() {

    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.creationTime = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SubmitterID", nullable = false)
    private User submitter;

    @Column(name = "CreationTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    @Column(name = "Title")
    @NotEmpty(message = "{validation.title.notEmpty}")
    private String title;

    @Column(name = "Content")
    @NotEmpty(message = "{validation.content.notEmpty}")
    private String content;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CourseID", nullable = false)
    private Course course;

    @ManyToMany(targetEntity = User.class, mappedBy = "likedPosts")
    private Set<User> studentsWhoLiked;

    @OneToMany(mappedBy = "post")
    private Set<PostComment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSubmitter() {
        return submitter;
    }

    public void setSubmitter(User submitter) {
        this.submitter = submitter;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<PostComment> getComments() {
        return comments;
    }

    public Set<User> getStudentsWhoLiked() { return studentsWhoLiked; }
}
