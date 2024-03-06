package com.aselmind.fsme.rest.master.superuser;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Getter
public class CreateSuperUserRequest {
    @NonNull
    private String username;
    @NonNull
    private String userPassword;
}
