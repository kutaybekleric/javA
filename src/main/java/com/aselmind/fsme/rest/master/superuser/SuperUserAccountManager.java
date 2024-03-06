package com.aselmind.fsme.rest.master.superuser;

import com.aselmind.fsme.rest.config.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SuperUserAccountManager {
    private final AuthenticationManager authenticationManager;

    public LoginResult login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        return LoginResult.builder()
                .username(authentication.getName())
                .token(JwtUtils.generateToken(null, authentication.getName(), request.isRememberMe()))
                .build();
    }
}
