package com.br.mailservice.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "emails")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String recipient;

    @Column(nullable=false)
    private String subject;

    @Column(nullable=false, columnDefinition="TEXT")
    private String content;

    @Column(nullable=false)
    private LocalDateTime sentDate;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private EmailStatus status;
}
