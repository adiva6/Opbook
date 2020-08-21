package com.example.opbook.config;

import com.example.opbook.model.Course;
import com.example.opbook.model.User;
import com.example.opbook.service.UserService;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomMethodSecurityExpressionRoot
        extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {
    private Object returnObject;
    private Object filterObject;

    private UserService userService;

    public CustomMethodSecurityExpressionRoot(Authentication authentication, UserService userService) {
        super(authentication);
        this.userService = userService;
    }

    public boolean isEnrolledStudent(String courseSymbol) {
        UserDetails userDetails = ((UserDetails) this.getPrincipal());
        User user = userService.findByEmail(userDetails.getUsername());
        if (user.getIsAdmin()) {
            return true;
        }

        for (Course course: user.getAttendedCourses()) {
            if (course.getCourseSymbol().equals(courseSymbol)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void setFilterObject(Object o) {
        this.filterObject = o;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object o) {
        this.returnObject = o;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }
}
