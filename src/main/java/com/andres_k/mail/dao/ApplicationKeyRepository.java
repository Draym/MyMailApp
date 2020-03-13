package com.andres_k.mail.dao;

import com.andres_k.mail.models.ApplicationKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationKeyRepository extends JpaRepository<ApplicationKey, Long> {
    ApplicationKey findByApiKey(String apiKey);
}
