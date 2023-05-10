package com.aws.inzynierka.exception.exceptions;

public class UserExistsException extends AppException {
    public UserExistsException(String message) {
        super(message);
        this.code = 400;
    }
}
