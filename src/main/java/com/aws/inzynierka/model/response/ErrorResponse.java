package com.aws.inzynierka.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private int errorCode;
    private String errorMessage;
    private Instant timestamp;
}
