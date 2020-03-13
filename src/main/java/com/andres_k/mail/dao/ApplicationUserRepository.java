package com.andres_k.mail.dao;

import com.andres_k.mail.models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    Boolean existsByEmail(String email);
}
