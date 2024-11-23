package com.br.mailservice.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ResponseDTO<T> {
    private T data;
    private LocalDateTime time = LocalDateTime.now();

    public ResponseDTO(T data) {
        this.data = data;
    }
}