package com.example.opbook.model;

import com.example.opbook.parser.CourseRatingJsonDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@JsonDeserialize(using = CourseRatingJsonDeserializer.class)
@Entity
@Table(name = "CourseRating")
public class CourseRating {
    public CourseRating() {

    }

    public CourseRating(Integer interest, Integer instruction, Integer relevance) {
        this.instruction = instruction;
        this.interest = interest;
        this.relevance = relevance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Min(1)
    @Max(5)
    @Column(name = "Interest")
    @NotNull(message = "{validation.interest.notNull}")
    private Integer interest;

    @Min(1)
    @Max(5)
    @Column(name = "Instruction")
    @NotNull(message = "{validation.instruction.notNull}")
    private Integer instruction;

    @Min(1)
    @Max(5)
    @Column(name = "Relevance")
    @NotNull(message = "{validation.relevance.notNull}")
    private Integer relevance;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CourseID", nullable = false)
    private Course course;

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

    public Integer getInterest() {
        return interest;
    }

    public void setInterest(Integer interest) {
        this.interest = interest;
    }

    public Integer getInstruction() {
        return instruction;
    }

    public void setInstruction(Integer instruction) {
        this.instruction = instruction;
    }

    public Integer getRelevance() {
        return relevance;
    }

    public void setRelevance(Integer relevance) {
        this.relevance = relevance;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
