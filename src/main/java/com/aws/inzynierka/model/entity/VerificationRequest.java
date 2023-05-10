package com.aws.inzynierka.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationRequest {
    @Id
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private UUID requestUuid = UUID.randomUUID();
    @NotBlank
    @Column(nullable = false)
    private String certificationId;
}
