package com.andres_k.mail.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity(name = "EmailSent")
@Table(name = "email_sent")
public class EmailSent {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name = "user_name")
    private String userName;
    @NotNull
    @Column(name = "user_email")
    private String userEmail;
    @NotNull
    @Column(name = "email_to")
    private String to;
    @NotNull
    @Column(name = "email_from")
    private String from;
    @NotNull
    @Column(name = "subject")
    private String subject;
    @NotNull
    @Column(name = "message", length = 1500)
    private String message;
    @NotNull
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @NotNull
    @Column(name = "app_id")
    private Long applicationId;

    public EmailSent() {
    }

    public EmailSent(String userName, String userEmail, String from, String to, String subject, String message) {
        this.setUserName(userName);
        this.setUserEmail(userEmail);
        this.setFrom(from);
        this.setTo(to);
        this.setSubject(subject);
        this.setMessage(message);
        this.setCreationDate(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        int size = 255;
        try {
            size = getClass().getDeclaredField("message").getAnnotation(Column.class).length();
        } catch (NoSuchFieldException ignored) {
        }
        int inLength = message.length();
        if (inLength > size) {
            message = message.substring(0, size);
        }
        this.message = message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
}
