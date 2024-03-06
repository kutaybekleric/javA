package com.aselmind.fsme.rest.master.superuser;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class LoginRequest {
    private String username;
    private String password;
    private boolean rememberMe;
}
