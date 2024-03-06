package com.aselmind.fsme.rest.master.company;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@Builder
public class UpdateCompanyRequest {
    @NotNull
    private String title;
}
