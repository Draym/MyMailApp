package com.andres_k.mail.services;

import com.andres_k.mail.dao.ApplicationKeyRepository;
import com.andres_k.mail.dao.ApplicationRepository;
import com.andres_k.mail.models.Application;
import com.andres_k.mail.models.ApplicationKey;
import org.springframework.stereotype.Service;

@Service
public class AuthAPIService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationKeyRepository applicationKeyRepository;

    public AuthAPIService(ApplicationRepository applicationRepository, ApplicationKeyRepository applicationKeyRepository) {
        this.applicationRepository = applicationRepository;
        this.applicationKeyRepository = applicationKeyRepository;
    }

    public Application authorize(String apiKey) throws Exception {
        ApplicationKey applicationKey = this.applicationKeyRepository.findByApiKey(apiKey);
        if (applicationKey == null) {
            throw new Exception("The API key {" + apiKey + "} is invalid.");
        }
        return this.applicationRepository.findById(applicationKey.getApplicationId()).orElse(null);
    }
}
