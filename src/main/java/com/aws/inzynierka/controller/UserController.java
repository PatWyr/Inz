package com.aws.inzynierka.controller;

import com.aws.inzynierka.model.request.CertificationResponse;
import com.aws.inzynierka.model.entity.VerificationRequest;
import com.aws.inzynierka.repository.VerificationRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final VerificationRequestRepository verificationRequestRepository;

    @PostMapping("/user-verification")
    public ResponseEntity verifyUserCertification(@RequestBody CertificationResponse request) {
        log.info("Received new request with certification id");
        verificationRequestRepository.save(VerificationRequest
                .builder()
//                .certificationId(request.getId())
                .build());
        return ResponseEntity.ok().build();
    }
}
