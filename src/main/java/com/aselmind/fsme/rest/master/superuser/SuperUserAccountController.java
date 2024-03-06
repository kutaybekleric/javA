package com.aselmind.fsme.rest.master.superuser;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/superuser/account")
public class SuperUserAccountController {
    private final SuperUserAccountManager superUserAccountManager;

    @PostMapping("/login")
    public LoginResult login(@RequestBody LoginRequest request) {
        return superUserAccountManager.login(request);
    }
}
