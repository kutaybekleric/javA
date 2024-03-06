package com.aselmind.fsme.rest.company.property;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@Builder
public class CreateProperyRequest {
    private String id;
    private String value;
}
