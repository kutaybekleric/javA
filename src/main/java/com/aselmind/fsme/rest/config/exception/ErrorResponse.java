package com.aselmind.fsme.rest.config.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@Jacksonized
@Builder
public class ErrorResponse {
    @Singular
    private List<ErrorBody> errors;
}
