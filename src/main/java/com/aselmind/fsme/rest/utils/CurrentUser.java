package com.aselmind.fsme.rest.utils;

import com.aselmind.fsme.rest.master.company.CompanyEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import org.springframework.security.core.userdetails.UserDetails;

@Jacksonized
@Builder
@Getter
public class CurrentUser {
    private CompanyEntity companyEntity;
    private UserDetails userDetails;
}
