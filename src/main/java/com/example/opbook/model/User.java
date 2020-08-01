package com.example.opbook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Name")
    @NotEmpty(message = "{validation.name.notEmpty}")
    private String name;

    @Column(name = "Email")
    @Email(message = "{validation.email.valid")
    @NotEmpty(message = "{validation.email.notEmpty}")
    private String email;

    @Column(name = "Password")
    @JsonIgnore
    @NotEmpty(message = "{validation.password.notEmpty}")
    private String password;

    @Column(name = "IsAdmin")
    private Boolean isAdmin;

    @ManyToMany
    @JoinTable(name="UserCourses",
            joinColumns=@JoinColumn(name="UserID"),
            inverseJoinColumns=@JoinColumn(name="CourseID"))
    private Set<Course> attendedCourses;

    @ManyToMany
    @JoinTable(name="PostLikes",
            joinColumns=@JoinColumn(name="UserID"),
            inverseJoinColumns=@JoinColumn(name="PostID"))
    private Set<Post> likedPosts;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsAdmin() { return isAdmin; }

    public void setIsAdmin(Boolean isAdmin) { this.isAdmin = isAdmin; }

}
