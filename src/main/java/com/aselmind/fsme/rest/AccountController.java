package com.aselmind.fsme.rest;

import com.aselmind.fsme.rest.config.security.annotations.IsAuthenticated;
import com.aselmind.fsme.rest.utils.CurrentUser;
import com.aselmind.fsme.rest.utils.RequestUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
@IsAuthenticated
public class AccountController {

    @GetMapping
    public CurrentUser me(){
       return RequestUtils.getCurrentUser().orElse(null);
    }
}
