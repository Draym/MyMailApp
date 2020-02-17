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
    @Column(name = "message")
    private String message;
    @NotNull
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    public EmailSent() {
    }

    public EmailSent(String userName, String userEmail, String from, String to, String subject, String message) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.message = message;
        this.creationDate = LocalDateTime.now();
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
        this.message = message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
