package com.aselmind.fsme.rest.config.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException() {
        super(HttpStatus.BAD_REQUEST);
    }
}
