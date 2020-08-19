package com.example.opbook.model;

import com.example.opbook.parser.LectureCommentJsonDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@JsonDeserialize(using = LectureCommentJsonDeserializer.class)
@Entity
@Table(name = "LectureComment")
public class LectureComment {
    public LectureComment() {

    }

    public LectureComment(String content) {
        this.content = content;
        this.creationTime = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "LectureID", nullable = false)
    private Lecture lecture;

    @Column(name = "CreationTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    @Column(name = "ReferenceTimeSeconds")
    private Integer referenceTimeSeconds;

    @Column(name = "Content")
    @NotEmpty(message = "{validation.content.notEmpty}")
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public Integer getReferenceTimeSeconds() {
        return referenceTimeSeconds;
    }

    public void setReferenceTimeSeconds(Integer referenceTimeSeconds) {
        this.referenceTimeSeconds = referenceTimeSeconds;
    }

    public Date getCreationTime() { return creationTime; }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
