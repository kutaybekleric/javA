package com.aselmind.fsme.rest.company.user;

import com.aselmind.fsme.rest.company.CompanyDbExecute;
import com.aselmind.fsme.rest.config.security.JwtUtils;
import com.aselmind.fsme.rest.master.company.CompanyEntity;
import com.aselmind.fsme.rest.master.company.CompanyManager;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyUserAccountManager  {

    private final AuthenticationManager authenticationManager;
    private final CompanyDbExecute companyDbExecute;
    private final CompanyManager companyManager;



    public LoginResult login(LoginRequest request) {
        CompanyEntity companyEntity = companyManager.findByCode(request.getCompanyCode()).orElseThrow(() ->  new UsernameNotFoundException("User not found"));

        Authentication authentication = companyDbExecute.run(companyEntity,() -> authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        ));

        return LoginResult.builder()
                .username(authentication.getName())
                .companyCode(companyEntity.getCode())
                .token(JwtUtils.generateToken(companyEntity, authentication.getName(), request.isRememberMe()))
                .build();
    }
}
