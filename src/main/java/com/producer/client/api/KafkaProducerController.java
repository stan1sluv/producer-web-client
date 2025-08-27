package com.producer.client.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.producer.client.api.dto.ResponseDTO;
import com.producer.client.service.KafkaProducerService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/v1/producer")
@RequiredArgsConstructor
public class KafkaProducerController {
    
    private final KafkaProducerService kafkaService;
    
    @PostMapping("/send")
    public ResponseEntity<ResponseDTO> sendMessage(
        @Validated
        @RequestParam(required = false) String topic,
        @RequestBody Object message) {
        var result = kafkaService.send(topic, message);
        return new ResponseEntity<>(result, result.getStatus() == "SUCCESS" ? 
            HttpStatusCode.valueOf(200) : HttpStatusCode.valueOf(500));
    }
}
