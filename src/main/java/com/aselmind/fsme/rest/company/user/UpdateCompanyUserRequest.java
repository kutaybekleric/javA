package com.aselmind.fsme.rest.company.user;

import com.aselmind.fsme.rest.config.security.SecurityRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@Builder
public class UpdateCompanyUserRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String userPassword;
    @NotNull
    private SecurityRole securityRole;
}
