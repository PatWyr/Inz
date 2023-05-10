package com.aws.inzynierka.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Builder
public class UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 7156526077883281623L;
    private UUID uuid;
    private String username;
    private Boolean isCertified;
}
