package com.br.mailservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}