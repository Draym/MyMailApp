package com.andres_k.mail.dao;

import com.andres_k.mail.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Application findByNameAndBaseUrl(String name, String allowedUrl);
    Boolean existsByNameAndBaseUrl(String name, String allowedUrl);
}
