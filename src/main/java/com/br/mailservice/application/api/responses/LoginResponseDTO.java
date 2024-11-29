package com.br.mailservice.application.api.responses;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;

    public LoginResponseDTO(String token) {
        this.token = token;
    }
}