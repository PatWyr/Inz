package com.aws.inzynierka.exception.exceptions;

public class AuthenticationException extends AppException {
    public AuthenticationException(String message) {
        super(message);
        this.code = 403;
    }
}
