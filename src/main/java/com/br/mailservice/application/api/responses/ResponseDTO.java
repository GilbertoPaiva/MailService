package com.br.mailservice.application.api.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class ResponseDTO<T> {
    private T data;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime time = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

    public ResponseDTO(T data) {
        this.data = data;
    }
}
