package com.andres_k.mail.controllers;

import com.andres_k.mail.models.http.MessageCtn;
import com.andres_k.mail.services.EmailService;
import com.andres_k.mail.utils.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/email")
@CrossOrigin(origins = "http://localhost:3200", allowCredentials = "true")
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> userContact(@RequestBody MessageCtn message) {
        try {
            this.emailService.sendEmail(message);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception ex) {
            Console.log("[Message/contact]: " + ex.toString());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/toAdmin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> toAdmin(@RequestBody MessageCtn message) {
        try {
            this.emailService.sendToAdmin(message);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception ex) {
            Console.log("[Message/contact]: " + ex.toString());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
