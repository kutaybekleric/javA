package com.aselmind.fsme.rest.master.superuser;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Getter
public class UpdateSuperUserRequest {
    private String username;
}
