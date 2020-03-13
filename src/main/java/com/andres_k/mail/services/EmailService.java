package com.andres_k.mail.services;

import com.andres_k.mail.dao.ApplicationUserRepository;
import com.andres_k.mail.dao.EmailSentRepository;
import com.andres_k.mail.models.Application;
import com.andres_k.mail.models.ApplicationUser;
import com.andres_k.mail.models.EmailSent;
import com.andres_k.mail.models.http.MessageCtn;
import com.andres_k.mail.utils.managers.EmailManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;

@Service
public class EmailService {
    private final ApplicationUserRepository applicationUserRepository;
    private final EmailSentRepository emailSentRepository;
    private final EmailManager emailManager;

    @Autowired
    public EmailService(ApplicationUserRepository applicationUserRepository, EmailSentRepository emailSentRepository, EmailManager emailManager) {
        this.applicationUserRepository = applicationUserRepository;
        this.emailSentRepository = emailSentRepository;
        this.emailManager = emailManager;
    }

    public void sendEmail(Application app, MessageCtn message) throws MessagingException {
        EmailSent email  = this.emailManager.send(message);
        email.setApplicationId(app.getId());
        this.emailSentRepository.save(email);
    }

    public void sendToAdmin(Application app, MessageCtn message) throws MessagingException {
        if (!this.applicationUserRepository.existsByEmail(message.getEmail())) {
            ApplicationUser applicationUser = new ApplicationUser();
            applicationUser.setEmail(message.getEmail());
            applicationUser.setApplicationId(app.getId());
            this.applicationUserRepository.save(applicationUser);
        }
        EmailSent email  = this.emailManager.sendToAdmin(message);
        email.setApplicationId(app.getId());
        this.emailSentRepository.save(email);
    }
}
