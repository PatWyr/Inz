package com.aws.inzynierka.repository;

import com.aws.inzynierka.model.entity.VerificationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRequestRepository extends JpaRepository<VerificationRequest, Long> {
}
