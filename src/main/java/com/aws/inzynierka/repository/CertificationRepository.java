package com.aws.inzynierka.repository;

import com.aws.inzynierka.model.entity.CertificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository extends JpaRepository<CertificationType, Long> {
    CertificationType findCertificationTypeByName(String name);
}
