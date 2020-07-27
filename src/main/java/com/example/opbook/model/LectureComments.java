package com.example.opbook.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="LectureComments")
public class LectureComments {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="UserID", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="LectureID", nullable=false)
    private Lecture lecture;

    @Temporal(TemporalType.TIMESTAMP)
    private Date referenceTime;

    private String content;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Lecture getLecture() { return lecture; }

    public void setLecture(Lecture lecture) { this.lecture = lecture; }

    public Date getReferenceTime() { return referenceTime; }

    public void setReferenceTime(Date referenceTime) { this.referenceTime = referenceTime; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

}
