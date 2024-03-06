package com.aselmind.fsme.rest.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String type;

    public ApiException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.type = getClass().getSimpleName();
    }
}
