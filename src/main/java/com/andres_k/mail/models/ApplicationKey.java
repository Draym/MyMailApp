package com.andres_k.mail.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "AppKey")
@Table(name = "app_key")
public class ApplicationKey {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name = "app_id")
    private Long applicationId;
    @NotNull
    @Column(name = "api_key")
    private String apiKey;
    @NotNull
    @Column(name = "active")
    private boolean active;
    @NotNull
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    public ApplicationKey() {

    }

    public ApplicationKey(Application application) {
        this.active = true;
        this.creationDate = LocalDateTime.now();
        this.applicationId = application.getId();
        this.apiKey = UUID.randomUUID().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String key) {
        this.apiKey = key;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
