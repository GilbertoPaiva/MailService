package com.br.mailservice.application.api.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class EmailDTO {
    @NotBlank(message = "Destinatário é obrigatório")
    @Email(message = "E-mail inválido")
    private String recipient;

    @NotBlank(message = "Assunto é obrigatório")
    private String subject;

    @NotBlank(message = "Conteúdo é obrigatório")
    private String content;
}