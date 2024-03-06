package com.aselmind.fsme.rest.company.user;

import com.aselmind.fsme.rest.company.CompanyDbExecute;
import com.aselmind.fsme.rest.company.user.CompanyUserEntity;
import com.aselmind.fsme.rest.company.user.CompanyUserManager;
import com.aselmind.fsme.rest.company.user.CompanyUserRepository;
import com.aselmind.fsme.rest.company.user.CreateCompanyUserRequest;
import com.aselmind.fsme.rest.config.security.SecurityRole;
import com.aselmind.fsme.rest.master.company.CompanyEntity;
import com.aselmind.fsme.rest.master.company.CompanyHelper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CompanyUserHelper {
    public static final String DEFAULT_TEST_USER_OWNER = "testUserOwner";
    public static final String DEFAULT_TEST_USER_EMPLOYEE = "testUserEmployee";
    private final CompanyUserManager companyUserManager;
    private final CompanyUserRepository companyUserRepository;
    private final CompanyDbExecute companyDbExecute;

    public CompanyUserEntity getOrCreateTestUserOwner(CompanyEntity companyEntity) {
        return companyDbExecute.run(companyEntity,() -> {
            return companyUserRepository.findByUsername(DEFAULT_TEST_USER_OWNER).orElseGet(() ->
                    companyUserManager.create(CreateCompanyUserRequest.builder()
                            .securityRole(SecurityRole.ROLE_OWNER)
                            .username(DEFAULT_TEST_USER_OWNER)
                            .userPassword(DEFAULT_TEST_USER_OWNER)
                            .build()));
        });
    }

    public CompanyUserEntity getOrCreateTestUserEmployee(CompanyEntity companyEntity){
        return companyDbExecute.run(companyEntity,() -> {
            return companyUserRepository.findByUsername(DEFAULT_TEST_USER_EMPLOYEE).orElseGet(() ->
                    companyUserManager.create(CreateCompanyUserRequest.builder()
                           .securityRole(SecurityRole.ROLE_EMPLOYEE)
                           .username(DEFAULT_TEST_USER_EMPLOYEE)
                           .userPassword(DEFAULT_TEST_USER_EMPLOYEE)
                           .build()));
        });
    }
}
