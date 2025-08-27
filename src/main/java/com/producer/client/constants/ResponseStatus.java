package com.producer.client.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ResponseStatus {
    SUCCESS_STATUS("SUCCESS"),
    ERROR_STATUS("ERROR");

    private String status;
}
