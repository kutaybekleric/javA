package com.aselmind.fsme.rest.company.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class CompanyUserAccountController {
    private final CompanyUserAccountManager companyUserAccountManager;

    @PostMapping("/login")
    public LoginResult login(@RequestBody LoginRequest request) {
        return companyUserAccountManager.login(request);
    }
}
