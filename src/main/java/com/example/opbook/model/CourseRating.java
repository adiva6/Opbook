package com.example.opbook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="CourseRating")
public class CourseRating {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="UserID", nullable=false)
    private User user;

    @Column(name = "Interest")
    @NotEmpty(message = "Please provide the interest score")
    private Integer interest;

    @Column(name = "Instruction")
    @NotEmpty(message = "Please provide the instruction score")
    private Integer instruction;

    @Column(name = "Relevance")
    @NotEmpty(message = "Please provide the relevance score")
    private Integer relevance;

    @JsonIgnore
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
