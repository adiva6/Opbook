package com.example.opbook.model;

import javax.persistence.*;

@Entity
@Table(name="Lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private String link;

    private Integer order;

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
