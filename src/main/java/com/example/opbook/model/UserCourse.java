package com.example.opbook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "UserCourse")
public class UserCourse {
    public UserCourse() {

    }

    public UserCourse(User user, Course course) {
        this.user = user;
        this.course = course;
        this.userCourseId = new UserCourseId(user.getId(), course.getId());
    }

    @EmbeddedId
    @JsonIgnore
    private UserCourseId userCourseId;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false, insertable = false, updatable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CourseID", nullable = false, insertable = false, updatable = false)
    private Course course;
}
