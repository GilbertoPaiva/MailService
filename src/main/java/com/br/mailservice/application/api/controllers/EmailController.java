package com.br.mailservice.application.api.controllers;

import com.br.mailservice.application.api.dtos.EmailDTO;
import com.br.mailservice.application.api.responses.ResponseDTO;
import com.br.mailservice.application.services.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@Valid @RequestBody EmailDTO emailDTO) {
        emailService.queueEmail(emailDTO.getRecipient(), emailDTO.getSubject(), emailDTO.getContent());
        return ResponseEntity.ok(new ResponseDTO<>("E-mail enfileirado para envio"));
    }

    @GetMapping("/send-pending")
    public ResponseEntity<?> sendPendingEmails() {
        emailService.sendPendingEmails();
        return ResponseEntity.ok(new ResponseDTO<>("E-mails pendentes enviados"));
    }
}
