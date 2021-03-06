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
    public ResponseEntity<?> send(@RequestHeader String Origin, @RequestHeader String ApiKey, @RequestBody MessageCtn message) {
        try {
            Application app = this.authAPIService.authorize(ApiKey);
            if (!this.authAPIService.verifyOriginAuthorization(Origin, app)) {
                return new ResponseEntity<>("API key used by wrong application", HttpStatus.FORBIDDEN);
            }
            this.emailService.sendEmail(app, message);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception ex) {
            Console.log("[Mail/send]: " + ex.toString());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> sendWithMessage(@RequestHeader String Origin, @RequestHeader String ApiKey, @RequestBody MessageCtn message) {
        try {
            Application app = this.authAPIService.authorize(ApiKey);
            if (!this.authAPIService.verifyOriginAuthorization(Origin, app)) {
                return new ResponseEntity<>("API key used by wrong application", HttpStatus.FORBIDDEN);
            }
            this.emailService.sendWithMessage(app, message);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception ex) {
            Console.log("[Mail/send]: " + ex.toString());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/toAdmin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> toAdmin(@RequestHeader String Origin, @RequestHeader String ApiKey, @RequestBody MessageCtn message) {
        try {
            Application app = this.authAPIService.authorize(ApiKey);
            if (!this.authAPIService.verifyOriginAuthorization(Origin, app)) {
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
