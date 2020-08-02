package com.example.opbook.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name="LectureComment")
public class LectureComment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="UserID", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="LectureID", nullable=false)
    private Lecture lecture;

    @Column(name = "ReferenceTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date referenceTime;

    @Column(name = "Content")
    @NotEmpty(message = "Please provide the comment content")
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
