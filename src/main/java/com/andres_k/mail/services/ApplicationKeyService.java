package com.andres_k.mail.services;

import com.andres_k.mail.dao.ApplicationKeyRepository;
import com.andres_k.mail.models.ApplicationKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationKeyService {
    private final ApplicationKeyRepository applicationKeyRepository;

    @Autowired
    public ApplicationKeyService(ApplicationKeyRepository applicationKeyRepository) {
        this.applicationKeyRepository = applicationKeyRepository;
    }

    public ApplicationKey save(ApplicationKey applicationKey) {
        return this.applicationKeyRepository.save(applicationKey);
    }

    public void deactivate(Long id) {
        ApplicationKey applicationKey = this.getById(id);

        if (applicationKey != null) {
            applicationKey.setActive(false);
            this.applicationKeyRepository.save(applicationKey);
        }
    }
    public void deactivateByKey(String key) {
        ApplicationKey applicationKey = this.applicationKeyRepository.findByApiKey(key);

        if (applicationKey != null) {
            applicationKey.setActive(false);
            this.applicationKeyRepository.save(applicationKey);
        }
    }

    public ApplicationKey getById(Long id) {
        return this.applicationKeyRepository.findById(id).orElse(null);
    }

    public List<ApplicationKey> getAll() {
        return this.applicationKeyRepository.findAll();
    }
}
