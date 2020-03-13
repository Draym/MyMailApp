package com.andres_k.mail.utils.managers;

import com.andres_k.mail.models.EmailSent;
import com.andres_k.mail.models.http.MessageCtn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

@Service
public class EmailManager {
    private final Properties properties;
    private final Session session;

    public EmailManager() throws IOException {
        Resource resource = new ClassPathResource("/smtp_mail.properties");
        this.properties = PropertiesLoaderUtils.loadProperties(resource);

        this.session = Session.getInstance(this.properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("username"), properties.getProperty("password"));
            }
        });
    }

    private void addFooter(StringBuilder body) {
        body.append("<br/>");
        body.append("You can message us, by replying this email.");
        body.append("<br/>");
        body.append("<br/>");
        body.append("Regards, Draymlab.fr");
    }

    public EmailSent send(MessageCtn message) throws MessagingException {
        StringBuilder body = new StringBuilder();
        body.append(message.getName());
        body.append(",");
        body.append("<br/>");
        body.append(message.getMessage());
        body.append("<br/>");
        this.addFooter(body);

        this.send(this.properties.getProperty("from"), message.getEmail(), message.getSubject(), body.toString());
        return new EmailSent(message.getName(), message.getEmail(), this.properties.getProperty("from"), message.getEmail(), message.getSubject(), body.toString());
    }

    public EmailSent sendToAdmin(MessageCtn message) throws MessagingException {
        StringBuilder body = new StringBuilder();
        body.append(message.getName());
        body.append(" has sent a message:");
        body.append("<br/>");
        body.append(message.getMessage());
        body.append("<br/>");

        this.send(message.getEmail(), this.properties.getProperty("from"), message.getSubject(), body.toString());
        return new EmailSent(message.getName(), message.getEmail(), message.getEmail(), this.properties.getProperty("from"), message.getSubject(), body.toString());
    }

    public void send(String from, String to, String subject, String body) throws MessagingException {

        Message mail = new MimeMessage(this.session);
        mail.setFrom(new InternetAddress(from));
        mail.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(to));
        mail.setSubject(subject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(body, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        mail.setContent(multipart);

        Transport.send(mail);
    }
}
