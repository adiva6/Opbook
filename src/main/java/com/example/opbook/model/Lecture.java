package com.example.opbook.model;

import com.example.opbook.parser.LectureJsonDeserializer;
import com.example.opbook.validator.VideoConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@JsonDeserialize(using = LectureJsonDeserializer.class)
@Entity
@Table(name = "Lecture")
public class Lecture {
    public Lecture() {

    }

    public Lecture(String name, String videoId) {
        this.name = name;
        this.videoId = videoId;
        this.creationTime = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Name")
    @NotEmpty(message = "{validation.name.notEmpty}")
    private String name;

    @VideoConstraint
    @Column(name = "VideoID")
    @NotEmpty(message = "{validation.videoId.notEmpty}")
    private String videoId;

    @Column(name = "CreationTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CourseID", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "lecture")
    private Set<LectureComment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public Date getCreationTime() { return creationTime; }

    public void setCreationTime(Date creationTime) { this.creationTime = creationTime; }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<LectureComment> getComments() { return comments; }
}
