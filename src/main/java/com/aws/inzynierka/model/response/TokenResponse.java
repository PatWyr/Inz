package com.aws.inzynierka.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TokenResponse {
    private String authenticationToken;
    private String expiresAt;
    private String issuedAt;
    private String username;
    private List<String> roles;
}
