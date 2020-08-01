package com.example.opbook.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="SubmitterID", nullable=false)
    private User submitter;

    @Column(name = "CreationTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    @Column(name = "Title")
    @NotEmpty(message = "Please provide the post title")
    private String title;

    @Column(name = "Content")
    @NotEmpty(message = "Please provide the post content")
    private String content;

    @ManyToOne
    @JoinColumn(name="CourseID", nullable=false)
    private Course course;

    @ManyToMany(mappedBy="likedPosts")
    private Set<User> studentsWhoLiked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getSubmitter() { return submitter; }

    public void setSubmitter(User submitter) { this.submitter = submitter; }

    public Date getCreationTime() { return creationTime; }

    public void setCreationTime(Date creationTime) { this.creationTime = creationTime; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content;}

    public Course getCourse() { return course; }

    public void setCourse(Course course) { this.course = course;}
}
