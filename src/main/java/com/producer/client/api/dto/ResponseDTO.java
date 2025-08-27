package com.producer.client.api.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.producer.client.constants.ResponseMessage;
import com.producer.client.constants.ResponseStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDTO {
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime timestamp;
    private String status;
    private String message;
    private String error;

    public ResponseDTO (ResponseStatus status, ResponseMessage message, String error) {
        this.timestamp = LocalDateTime.now();
        this.status = status.getStatus();
        this.message = message.getMessage();
        this.error = error == null ? null : error;
    }
}
