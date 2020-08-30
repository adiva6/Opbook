package com.example.opbook.model;

import com.example.opbook.validator.EmailConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "User")
public class User {
    private static final String ADMIN_ROLE_NAME = "ROLE_ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Name")
    @NotEmpty(message = "{validation.name.notEmpty}")
    private String name;

    @EmailConstraint
    @Column(name = "Email")
    @Email(message = "{validation.email.valid")
    @NotEmpty(message = "{validation.email.notEmpty}")
    private String email;

    @Column(name = "Password")
    @NotEmpty(message = "{validation.password.notEmpty}")
    @JsonIgnore
    private String password;

    @Column(name = "IsAdmin")
    private Boolean isAdmin;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "UserCourse",
            joinColumns = @JoinColumn(name = "UserID"),
            inverseJoinColumns = @JoinColumn(name = "CourseID"))
    private Set<Course> attendedCourses;

    @ManyToMany(targetEntity = Post.class)
    @JoinTable(name = "PostLike",
            joinColumns = @JoinColumn(name = "UserID"),
            inverseJoinColumns = @JoinColumn(name = "PostID"))
    private Set<Post> likedPosts;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Set<Course> getAttendedCourses() {
        return attendedCourses;
    }

    @JsonIgnore
    public ArrayList<GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        if (this.getIsAdmin()) {
            authorities.add(new SimpleGrantedAuthority(ADMIN_ROLE_NAME));
        }

        return authorities;
    }
}
