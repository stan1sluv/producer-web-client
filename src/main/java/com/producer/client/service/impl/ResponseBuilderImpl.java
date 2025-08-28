package com.producer.client.service.impl;

import com.producer.client.api.dto.ResponseDTO;
import com.producer.client.service.ResponseBuilder;

import org.springframework.stereotype.Component;

import static com.producer.client.constants.ResponseMessage.*;
import static com.producer.client.constants.ResponseStatus.*;

@Component
public class ResponseBuilderImpl implements ResponseBuilder {

    @Override
    public ResponseDTO success() {
        return new ResponseDTO(SUCCESS_STATUS, SUCCESS_MESSAGE, null);
    }

    @Override
    public ResponseDTO error(String cause) {
        return new ResponseDTO(ERROR_STATUS, ERROR_MESSAGE, cause);
    }
}
