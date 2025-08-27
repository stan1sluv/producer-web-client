package com.producer.client.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.producer.client.api.dto.ResponseDTO;
import com.producer.client.service.ResponseBuilder;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class KafkaProducerExceprtionHandler {

    private final ResponseBuilder responseBuilder;

    @ExceptionHandler({
        HttpMessageNotReadableException.class, 
        HttpRequestMethodNotSupportedException.class,
        HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<ResponseDTO> handle(Exception ex) {
        var response = responseBuilder.error(getFormatedMessage(ex));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private String getFormatedMessage(Exception ex) {
        var i = ex.getMessage().indexOf(':');
        var message = ex.getMessage();
        return ex.getMessage().indexOf(':') != -1 ?
            message.substring(0, i).trim() :
            ex.getMessage();
    }
}
