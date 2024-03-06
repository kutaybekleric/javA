package com.aselmind.fsme.rest.master.superuser;

import com.aselmind.fsme.rest.master.superuser.CreateSuperUserRequest;
import com.aselmind.fsme.rest.master.superuser.SuperUserEntity;
import com.aselmind.fsme.rest.master.superuser.SuperUserManager;
import com.aselmind.fsme.rest.master.superuser.SuperUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SuperuserHelper {
    public static final String DEFAULT_TEST_USER = "testSuperUser";
    private final SuperUserRepository superUserRepository;
    private final SuperUserManager superUserManager;

    public SuperUserEntity getOrCreateTestSuperUser(){
        return superUserRepository.findByUsername(DEFAULT_TEST_USER).orElseGet(() ->
                superUserManager.create(CreateSuperUserRequest.builder()
                        .username(DEFAULT_TEST_USER)
                        .userPassword(DEFAULT_TEST_USER)
                .build()));
    }

}
