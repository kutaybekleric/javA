package com.aselmind.fsme.rest.master.company;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@Builder
public class CreateCompanyRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String code;
    @NotBlank
    private String dbNameSuffix;
}
