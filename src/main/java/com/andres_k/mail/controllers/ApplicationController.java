package com.andres_k.mail.controllers;

import com.andres_k.mail.models.Application;
import com.andres_k.mail.models.ApplicationKey;
import com.andres_k.mail.services.ApplicationService;
import com.andres_k.mail.utils.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/app")
public class ApplicationController {
    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> userContact(@RequestBody Application application) {
        try {
            if (application.getId() != null) {
                application.setId(null);
            }
            ApplicationKey result = this.applicationService.register(application);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            Console.log("[Message/contact]: " + ex.toString());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> get(@RequestParam String name, @RequestParam String url) {
        try {
            Application result = this.applicationService.get(name, url);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            Console.log("[Message/contact]: " + ex.toString());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAll() {
        try {
            List<Application> result = this.applicationService.getAll();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            Console.log("[Message/contact]: " + ex.toString());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
