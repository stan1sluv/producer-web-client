package com.producer.client.service;

import com.producer.client.api.dto.ResponseDTO;

public interface KafkaProducerService {
    ResponseDTO send(String topic, Object message);
}
