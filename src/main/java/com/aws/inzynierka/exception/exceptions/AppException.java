package com.aws.inzynierka.exception.exceptions;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    public int code;
    public AppException(String message) {
        super(message);
        this.code = 500;
    }
}
