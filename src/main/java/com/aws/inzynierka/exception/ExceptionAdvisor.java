package com.aws.inzynierka.exception;

import com.aws.inzynierka.exception.exceptions.AuthenticationException;
import com.aws.inzynierka.exception.exceptions.UserExistsException;
import com.aws.inzynierka.model.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler(value = UserExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserExistsException(UserExistsException ex) {
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .errorMessage(ex.getMessage())
                        .errorCode(ex.getCode())
                        .timestamp(Instant.now())
                        .build());
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthorizationException(AuthenticationException ex) {
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .errorMessage(ex.getMessage())
                        .errorCode(ex.getCode())
                        .timestamp(Instant.now())
                        .build());
    }
}
