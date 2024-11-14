package com.br.mailservice.runner;

import com.br.mailservice.service.EmailService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class EmailRunner implements CommandLineRunner {

    private final EmailService emailService;

    public EmailRunner(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void run(String... args) {
        // Gerar uma lista de 100 destinatários para teste
        List<String> recipients = IntStream.range(0, 100)     // List.of("user1@example.com", "user2@example.com"); para enviar para emails especificos
                .mapToObj(i -> "user" + i + "@example.com")
                .collect(Collectors.toList());

        String subject = "Teste"; // Assunto do email
        String templateName = "emailTemplate"; // Nome do template sem a extensão .html

        // Variáveis para o template
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", "User");

        // Enviar e-mails em massa
        emailService.sendBulkEmail(recipients, subject, templateName, variables);
    }
}