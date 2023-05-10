package com.aws.inzynierka.service;

import com.aws.inzynierka.model.entity.CertificationType;
import com.aws.inzynierka.model.entity.Post;
import com.aws.inzynierka.model.request.CertificationResponse;
import com.aws.inzynierka.model.response.CertificationRequest;
import com.aws.inzynierka.repository.CertificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CertificationService {

    private final CertificationRepository certificationRepository;

    @Transactional(readOnly = true)
    public List<CertificationResponse> retrieveAllCertifications() {
        List<CertificationType> certifications = certificationRepository.findAll();
        return certifications.stream().map(cert -> new CertificationResponse(cert.getId(), cert.getName(), cert.getDescription(), cert.getPosts().size())
        ).collect(Collectors.toList());
    }

    public void createCertification(CertificationRequest certificationRequest) {
        certificationRepository.save(CertificationType.builder()
                .name(certificationRequest.getName())
                .description(certificationRequest.getDescription())
                .build());
    }
}
