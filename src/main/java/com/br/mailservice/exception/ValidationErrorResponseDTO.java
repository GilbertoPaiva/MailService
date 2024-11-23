package com.br.mailservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Map;

@Data
@AllArgsConstructor
public class ValidationErrorResponseDTO {
    private String message;
    private Map<String, String> errors;
}