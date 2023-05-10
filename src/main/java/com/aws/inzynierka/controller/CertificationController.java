package com.aws.inzynierka.controller;

import com.aws.inzynierka.model.request.CertificationResponse;
import com.aws.inzynierka.model.response.CertificationRequest;
import com.aws.inzynierka.service.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/certification")
@RequiredArgsConstructor
public class CertificationController {

    private final CertificationService certificationService;

    @GetMapping("/list-certifications")
    public ResponseEntity<List<CertificationResponse>> getAllCertifications() {
        return ResponseEntity.ok(certificationService.retrieveAllCertifications());
    }

    @PostMapping("/create-certification")
    public ResponseEntity<Void> createCertification(@RequestBody CertificationRequest certificationRequest) {
        certificationService.createCertification(certificationRequest);
        return ResponseEntity.ok().build();
    }


}
