package com.br.mailservice.application.services;

import com.br.mailservice.domain.models.Email;
import com.br.mailservice.domain.models.EmailStatus;
import com.br.mailservice.domain.repository.EmailRepository;
import com.br.mailservice.infrastruture.mail.MailSenderService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmailService {
    private final EmailRepository emailRepository;
    private final MailSenderService mailSenderService;

    public EmailService(EmailRepository emailRepository, MailSenderService mailSenderService) {
        this.emailRepository = emailRepository;
        this.mailSenderService = mailSenderService;
    }

    public Email queueEmail(String recipient, String subject, String content) {
        Email email = new Email();
        email.setRecipient(recipient);
        email.setSubject(subject);
        email.setContent(content);
        email.setStatus(EmailStatus.PENDING);
        email.setSentDate(LocalDateTime.now());

        return emailRepository.save(email);
    }

    @Async
    public void sendPendingEmails() {
        List<Email> pendingEmails = emailRepository.findByStatus(EmailStatus.PENDING);
        for (Email email : pendingEmails) {
            try {
                mailSenderService.sendEmail(email.getRecipient(), email.getSubject(), email.getContent());
                email.setStatus(EmailStatus.SENT);
            } catch (Exception e) {
                email.setStatus(EmailStatus.FAILED);
            }
            emailRepository.save(email);
        }
    }
}
