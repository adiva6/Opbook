package com.example.opbook.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="CourseRating")
public class CourseRating {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="UserID", nullable=false)
    private User user;

    private Integer interest;

    private Integer instruction;

    private Integer relevance;

    @ManyToOne
    @JoinColumn(name="CourseID", nullable=false)
    private Course course;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Integer getInterest() { return interest; }

    public void setInterest(Integer interest) { this.interest = interest; }

    public Integer getInstruction() { return instruction; }

    public void setInstruction(Integer instruction) { this.instruction = instruction; }

    public Integer getRelevance() { return relevance; }

    public void setRelevance(Integer relevance) { this.relevance = relevance; }

    public Course getCourse() { return course; }

    public void setCourse(Course course) { this.course = course;}

}
