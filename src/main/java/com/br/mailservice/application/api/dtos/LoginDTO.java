package com.br.mailservice.application.api.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class LoginDTO {
    @NotBlank(message = "username.required.exception")
    @Email(message = "username.email.exception")
    private String username;

    @NotBlank(message = "password.required.exception")
    @Pattern(
            message = "password.pattern.exception",
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d%'\\/\"$#@_?&!\\-|*.,]{8,}$"
    )
    private String password;
}
