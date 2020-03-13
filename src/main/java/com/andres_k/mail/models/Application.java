package com.andres_k.mail.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "Aplication")
@Table(name = "application")
public class Application {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @NotNull
    @Column(name = "base_url")
    private String baseUrl;
    @Column(name = "desc_url")
    private String descUrl;

    public void update(Application application) {
        this.baseUrl = application.baseUrl;
        this.descUrl = application.descUrl;
        this.description = application.description;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getDescUrl() {
        return descUrl;
    }

    public void setDescUrl(String descUrl) {
        this.descUrl = descUrl;
    }
}
