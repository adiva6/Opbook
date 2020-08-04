package com.example.opbook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Name")
    @NotEmpty(message = "Please provide the lecture name")
    private String name;

    @Column(name = "Link")
    @NotEmpty(message = "Please provide the lecture link")
    private String link;

    @Column(name = "Order")
    @NotEmpty(message = "Please provide the lecture order")
    private Integer order;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="CourseID", nullable=false)
    private Course course;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLink() { return link; }

    public void setLink(String link) { this.link = link; }

    public Integer getOrder() { return order; }

    public void setOrder(Integer order) { this.order = order; }

    public Course getCourse() { return course; }

    public void setCourse(Course course) { this.course = course; }
}
