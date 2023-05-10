package com.aws.inzynierka.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CertificationResponse {
    private Long id;
    private String name;
    private String description;
    private Integer numberOfPosts;
}
