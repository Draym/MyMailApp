package com.andres_k.mail.services;

import com.andres_k.mail.dao.ApplicationRepository;
import com.andres_k.mail.models.Application;
import com.andres_k.mail.models.ApplicationKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationKeyService applicationKeyService;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, ApplicationKeyService applicationKeyService) {
        this.applicationRepository = applicationRepository;
        this.applicationKeyService = applicationKeyService;
    }

    public Application update(Application application) throws Exception {
        Application old = this.applicationRepository.findById(application.getId()).orElse(null);

        if (old == null) {
            throw new Exception("There is no application for Id=" + application.getId());
        }
        old.update(application);
        return this.applicationRepository.save(old);
    }

    public ApplicationKey register(Application application) throws Exception {
        if (this.applicationRepository.existsByNameAndBaseUrl(application.getName(), application.getBaseUrl())) {
            throw new Exception("This application {" + application.getName() + "," + application.getBaseUrl() + "} already exists.");
        }
        application = this.applicationRepository.save(application);

        ApplicationKey applicationKey = new ApplicationKey(application);
        this.applicationKeyService.save(applicationKey);

        return applicationKey;
    }

    public Application get(String name, String url) {
        return this.applicationRepository.findByNameAndBaseUrl(name, url);
    }

    public List<Application> getAll() {
        return this.applicationRepository.findAll();
    }

    public void delete(Long id) {
        this.applicationRepository.deleteById(id);
    }
}
