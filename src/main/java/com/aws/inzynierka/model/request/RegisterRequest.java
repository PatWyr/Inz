package com.aws.inzynierka.model.request;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
public class RegisterRequest {
    @Email
    private String email;
    @Size(min = 3, max = 50)
    private String username;
    @Size(min = 3, max = 50)
    private String password;
}
