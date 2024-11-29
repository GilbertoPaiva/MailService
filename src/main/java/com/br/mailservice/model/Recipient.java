package com.br.mailservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "recipients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipient {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @ManyToOne
    @JoinColumn(name = "email_id", nullable = false)
    private Email email;

    @Enumerated(EnumType.STRING)
    private RecipientStatus status = RecipientStatus.PENDING;

}