package com.example.opbook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="Course")
public class Course {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Name", unique = true)
    @NotEmpty(message = "Please provide a name")
    private String name;

    @Column(name = "CourseSymbol", unique = true)
    @NotEmpty(message = "Please provide a course symbol")
    private String courseSymbol;

    @Column(name = "CreationTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    @Column(name = "Image")
    private String image;

    @JsonIgnore
    @ManyToMany(mappedBy = "attendedCourses")
    private Set<User> students;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private Set<Post> posts;

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

    public String getCourseSymbol() { return courseSymbol; }

    public void setCourseSymbol(String courseSymbol) { this.courseSymbol = courseSymbol; }

    public Date getCreationTime() { return creationTime; }

    public void setCreationTime(Date creationTime) { this.creationTime = creationTime; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public Set<Post> getPosts() { return posts; }

}
