package com.br.mailservice.domain.repository;

import com.br.mailservice.domain.models.Email;
import com.br.mailservice.domain.models.EmailStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {
    List<Email> findByStatus(EmailStatus status);
}
