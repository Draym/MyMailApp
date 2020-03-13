package com.andres_k.mail.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "AppUser")
@Table(name = "app_user")
public class ApplicationUser {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name = "app_id")
    private Long applicationId;
    @NotNull
    @Column(name = "email")
    private String email;
    @Column(name = "tags")
    private String tags;
    @Column(name = "cookie")
    private String cookie;
    @Column(name = "origin")
    private String origin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
}
