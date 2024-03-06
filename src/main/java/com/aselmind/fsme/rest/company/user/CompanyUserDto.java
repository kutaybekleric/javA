package com.aselmind.fsme.rest.company.user;

import com.aselmind.fsme.rest.config.security.SecurityRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PACKAGE)
public class CompanyUserDto {
    private final String id;
    private final String username;
    private final SecurityRole securityRole;

    public CompanyUserDto(CompanyUserEntity entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.securityRole = entity.getSecurityRole();
    }
}
