package com.andres_k.mail.controllers;

import com.andres_k.mail.models.Application;
import com.andres_k.mail.models.ApplicationKey;
import com.andres_k.mail.services.ApplicationKeyService;
import com.andres_k.mail.services.ApplicationService;
import com.andres_k.mail.utils.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final ApplicationService applicationService;
    private final ApplicationKeyService applicationKeyService;

    @Autowired
    public AdminController(ApplicationService applicationService, ApplicationKeyService applicationKeyService) {
        this.applicationService = applicationService;
        this.applicationKeyService = applicationKeyService;
    }

    @RequestMapping(value = "/app/get", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getApp(@RequestParam String name, @RequestParam String url) {
        try {
            Application result = this.applicationService.get(name, url);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            Console.log("[Message/contact]: " + ex.toString());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/app/update", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody Application application) {
        try {
            if (application.getId() == null) {
                return new ResponseEntity<>("Application Id is mandatory.", HttpStatus.FORBIDDEN);
            }
            Application result = this.applicationService.update(application);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            Console.log("[Message/contact]: " + ex.toString());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/app/delete", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> deleteApp(@RequestParam Long id) {
        try {
            this.applicationService.delete(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception ex) {
            Console.log("[Message/contact]: " + ex.toString());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/app/getAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAllApp() {
        try {
            List<Application> result = this.applicationService.getAll();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            Console.log("[Message/contact]: " + ex.toString());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/key/get", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getKey(@RequestParam Long id) {
        try {
            ApplicationKey result = this.applicationKeyService.getById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            Console.log("[Message/contact]: " + ex.toString());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/key/deactivate", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> deactivateKey(@RequestParam Long id) {
        try {
            this.applicationKeyService.deactivate(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception ex) {
            Console.log("[Message/contact]: " + ex.toString());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/key/deactivateByKey", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> deactivateKey(@RequestParam String key) {
        try {
            this.applicationKeyService.deactivateByKey(key);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception ex) {
            Console.log("[Message/contact]: " + ex.toString());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/key/getAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAll() {
        try {
            List<ApplicationKey> result = this.applicationKeyService.getAll();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            Console.log("[Message/contact]: " + ex.toString());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
