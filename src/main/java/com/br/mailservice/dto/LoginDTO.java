package com.br.mailservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank(message = "O nome de usuário é obrigatório")
    @Email(message = "O nome de usuário deve ser um email válido")
    private String username;

    @NotBlank(message = "A senha é obrigatória")
    private String password;
}