package com.br.mailservice.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private final ExecutorService executorService;
    private static final int MAX_RETRIES = 3;

    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.executorService = Executors.newFixedThreadPool(10); // Define o número de threads
    }

    public void sendBulkEmail(List<String> recipients, String subject, String templateName, Map<String, Object> variables) {
        for (String recipient : recipients) {
            executorService.submit(() -> {
                try {
                    sendEmailWithRetry(recipient, subject, templateName, variables, MAX_RETRIES);
                } catch (MessagingException | jakarta.mail.MessagingException e) {
                    System.err.println("Error sending email to " + recipient + ": " + e.getMessage());
                }
            });
        }
    }

    private void sendEmailWithRetry(String recipient, String subject, String templateName, Map<String, Object> variables, int retries) throws MessagingException, jakarta.mail.MessagingException {
        int attempt = 0;
        while (attempt < retries) {
            try {
                sendEmail(recipient, subject, templateName, variables);
                return; // Sucesso, sair do loop
            } catch (MessagingException | jakarta.mail.MessagingException e) {
                attempt++;
                if (attempt >= retries) {
                    throw e; // Lançar exceção após atingir o número máximo de tentativas
                }
                System.err.println("Retrying to send email to " + recipient + " (attempt " + attempt + " of " + retries + ")");
            }
        }
    }

    private void sendEmail(String recipient, String subject, String templateName, Map<String, Object> variables) throws MessagingException, jakarta.mail.MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(recipient);
        helper.setSubject(subject);

        // Add the 'sender' variable to the context
        variables.put("sender", "your_sender@example.com");

        // Render the template with Thymeleaf
        Context context = new Context();
        context.setVariables(variables);
        String htmlContent = templateEngine.process(templateName, context);

        helper.setText(htmlContent, true); // Set 'true' to send HTML

        mailSender.send(message);
    }
}