package com.andres_k.mail.services;

import com.andres_k.mail.dao.ApplicationKeyRepository;
import com.andres_k.mail.dao.ApplicationRepository;
import com.andres_k.mail.models.Application;
import com.andres_k.mail.models.ApplicationKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationKeyRepository applicationKeyRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, ApplicationKeyRepository applicationKeyRepository) {
        this.applicationRepository = applicationRepository;
        this.applicationKeyRepository = applicationKeyRepository;
    }

    public ApplicationKey register(Application application) throws Exception {
        if (this.applicationRepository.existsByNameAndBaseUrl(application.getName(), application.getBaseUrl())) {
            throw new Exception("This application {" + application.getName() + "," + application.getBaseUrl() + "} already exists.");
        }
        application = this.applicationRepository.save(application);

        ApplicationKey applicationKey = new ApplicationKey(application);
        this.applicationKeyRepository.save(applicationKey);

        return applicationKey;
    }

    public Application get(String name, String url) {
        return this.applicationRepository.findByNameAndBaseUrl(name, url);
    }

    public List<Application> getAll() {
        return this.applicationRepository.findAll();
    }
}
