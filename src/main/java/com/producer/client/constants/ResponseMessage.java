package com.producer.client.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ResponseMessage {
    SUCCESS_MESSAGE("Message was sent successfully"),
    ERROR_MESSAGE("Error while sending a message to a topic");

    private String message;
}
