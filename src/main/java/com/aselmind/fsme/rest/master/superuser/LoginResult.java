package com.aselmind.fsme.rest.master.superuser;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class LoginResult {
    private String username;
    private String token;
}
