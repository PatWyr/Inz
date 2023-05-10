package com.aws.inzynierka.controller;

import com.aws.inzynierka.service.AuthorizationService;
import com.inzynierka.generated.model.LoginRequest;
import com.inzynierka.generated.model.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController implements com.inzynierka.generated.api.AuthenticationApi {
    private final AuthorizationService authorizationService;

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        log.info("Received login request");
        return ResponseEntity.ok(authorizationService.login(loginRequest));
    }

    @Override
    public ResponseEntity<Void> signUp(com.inzynierka.generated.model.RegisterRequest registerRequest) {
        log.info("Received signup request");
        authorizationService.signup(registerRequest);
        return ResponseEntity.ok().build();
    }
}
