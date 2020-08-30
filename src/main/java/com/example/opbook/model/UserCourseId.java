package com.example.opbook.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserCourseId implements Serializable {
    private Long userId;

    private Long courseId;

    public UserCourseId() {

    }

    public UserCourseId(Long userId, Long courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    public Long getUserId() { return this.userId; }

    public Long getCourseId() { return this.courseId; }

    @Override
    public int hashCode() {
        return Objects.hash(userId, courseId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserCourseId other = (UserCourseId) obj;
        return Objects.equals(userId, other.getUserId())
                && Objects.equals(courseId, other.getCourseId());
    }
}
