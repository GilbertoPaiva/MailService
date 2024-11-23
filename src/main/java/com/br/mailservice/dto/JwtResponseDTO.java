package com.br.mailservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponseDTO {

    private String token;
    private String type = "Bearer";
    private String id;
    private String username;

    public JwtResponseDTO(String token, String id, String username) {
        this.token = token;
        this.id = id;
        this.username = username;
    }
}