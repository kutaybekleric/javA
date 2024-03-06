package com.aselmind.fsme.rest.config.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Jacksonized
@Builder
public class ErrorBody {
    private final int status;
    private final String type;
    private final String detail;
}