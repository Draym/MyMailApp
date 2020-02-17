package com.andres_k.mail.dao;

import com.andres_k.mail.models.EmailSent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailSentRepository extends JpaRepository<EmailSent, Long> {
}
