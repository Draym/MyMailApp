package com.andres_k.mail.services;

import com.andres_k.mail.dao.EmailSentRepository;
import com.andres_k.mail.models.EmailSent;
import com.andres_k.mail.models.http.MessageCtn;
import com.andres_k.mail.utils.managers.EmailManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

@Service
public class EmailService {
    private final EmailSentRepository emailSentRepository;

    @Autowired
    public EmailService(EmailSentRepository emailSentRepository) {
        this.emailSentRepository = emailSentRepository;
    }

    public void sendEmail(MessageCtn message) throws IOException, MessagingException {
        EmailManager.get().send(message);
        EmailSent email = new EmailSent();
    }

    public void sendToAdmin(MessageCtn message) throws IOException, MessagingException {
        EmailManager.get().sendToAdmin(message);
    }
}
