package com.aselmind.fsme.rest.company.user;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class LoginResult {
    private String username;
    private String companyCode;
    private String token;
}
