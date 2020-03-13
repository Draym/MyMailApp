package com.andres_k.mail.controllers;

import com.andres_k.mail.models.Application;
import com.andres_k.mail.models.http.MessageCtn;
import com.andres_k.mail.services.AuthAPIService;
import com.andres_k.mail.services.EmailService;
import com.andres_k.mail.utils.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@Controller
@CrossOrigin(origins = "*", allowCredentials = "true")
@RequestMapping(value = "/email")
public class EmailController {
    private final AuthAPIService authAPIService;
    private final EmailService emailService;

    @Autowired
    public EmailController(AuthAPIService authAPIService, EmailService emailService) {
        this.authAPIService = authAPIService;
        this.emailService = emailService;
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> userContact(HttpServletRequest request, @RequestHeader String ApiKey, @RequestBody MessageCtn message) {
        try {
            String origin = URI.create(request.getRequestURL().toString()).getHost();
            Application app = this.authAPIService.authorize(ApiKey);
            this.emailService.sendEmail(app, message);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception ex) {
            Console.log("[Mail/send]: " + ex.toString());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/toAdmin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> toAdmin(HttpServletRequest request, @RequestHeader String ApiKey, @RequestBody MessageCtn message) {
        try {
            String origin = URI.create(request.getRequestURL().toString()).getHost();
            Application app = this.authAPIService.authorize(ApiKey);
            if (!origin.equals(app.getBaseUrl())) {
                return new ResponseEntity<>("API key used by wrong application", HttpStatus.FORBIDDEN);
            }
            this.emailService.sendToAdmin(app, message);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception ex) {
            Console.log("[Mail/toAdmin]: " + ex.toString());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
