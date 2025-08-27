package com.producer.client.service;

import com.producer.client.api.dto.ResponseDTO;

public interface ResponseBuilder {
    ResponseDTO success();
    ResponseDTO error(String cause);
}
