package com.aselmind.fsme.rest.config.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {
    private final SecurityRole role;

    @Override
    public String getAuthority() {
        if (role != null) {
            return role.name();
        }
        return "";
    }
}
