package com.example.opbook.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Course")
public class Course {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private String courseSymbol;

    private Date creationTime;

    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

}
