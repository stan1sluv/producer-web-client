package com.producer.client.service.impl;

import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.producer.client.api.dto.ResponseDTO;
import com.producer.client.service.KafkaProducerService;
import com.producer.client.service.ResponseBuilder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final Logger log = Logger.getLogger(KafkaProducerServiceImpl.class.getName());
    private final ResponseBuilder responseBuilder;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Value("${spring.kafka.producer.topic.name}")
    private String defaultTopic;

    @Override
    public ResponseDTO send(String topic, Object message) {
        var targetTopic = topic == null ? defaultTopic : topic;
        log.info("start sending a message to a topic: " + targetTopic);
        String key = generateKey();
        log.info("generated key: " + key);
        try {
            kafkaTemplate.send(targetTopic, key, message);
            var responseDTO = responseBuilder.success();
            log.info("message was sent successfully");
            return responseDTO;
        } catch (Exception ex) {
            log.warning("error while sending a message");
            return responseBuilder.error(ex.getCause().toString());
        }
    }

    private String generateKey() {
        return UUID.randomUUID().toString();
    }
}
