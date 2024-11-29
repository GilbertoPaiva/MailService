package com.br.mailservice.application.api.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class RegisterDTO {
    @Pattern(
            message = "document.pattern.exception",
            regexp = "\\d{11}"
    )
    @NotBlank(message = "document.required.exception")
    private String document;

    @NotBlank(message = "name.required.exception")
    private String name;

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
