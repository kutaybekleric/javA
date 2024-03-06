package com.aselmind.fsme.rest.company.user;

import com.aselmind.fsme.rest.BaseIntegrationTest;
import com.aselmind.fsme.rest.RequestSpec;
import com.aselmind.fsme.rest.ResponseSpec;
import com.aselmind.fsme.rest.master.company.CompanyEntity;
import com.aselmind.fsme.rest.master.company.CompanyHelper;
import com.aselmind.fsme.rest.master.superuser.*;
import com.aselmind.fsme.rest.utils.ControllerUtils;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

class CompanyUserLoginTest extends BaseIntegrationTest {
    private final String PATH = ControllerUtils.getPath(CompanyUserAccountController.class) + "/login";

    @Autowired
    private CompanyUserHelper companyUserHelper;

    @Autowired
    private CompanyHelper companyHelper;

    @Test
    void testLogin200() {
        CompanyEntity companyEntity = companyHelper.getOrCreateTestCompany();
        CompanyUserEntity companyUserEntity = companyUserHelper.getOrCreateTestUserOwner(companyEntity);

        ValidatableResponse then = RequestSpec.when().jsonRequest()
                .body(LoginRequest.builder()
                        .companyCode(companyEntity.getCode())
                        .username(CompanyUserHelper.DEFAULT_TEST_USER_OWNER)
                        .password(CompanyUserHelper.DEFAULT_TEST_USER_OWNER)
                        .build())
                .post(PATH)
                .then()
                .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.OK));
        LoginResult result = then.extract().as(LoginResult.class);
        assert result.getUsername().equals(companyUserEntity.getUsername());
    }

    @Test
    void testWrongCredentials(){
        CompanyEntity companyEntity = companyHelper.getOrCreateTestCompany();

        ValidatableResponse then = RequestSpec.when().jsonRequest()
               .body(LoginRequest.builder()
                       .companyCode(companyEntity.getCode())
                       .username(CompanyUserHelper.DEFAULT_TEST_USER_OWNER)
                       .password("<PASSWORD>")
                       .build())
               .post(PATH)
               .then()
               .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.UNAUTHORIZED));
    }

    @Test
    void testNotExistingUser(){
        CompanyEntity companyEntity = companyHelper.getOrCreateTestCompany();

        ValidatableResponse then = RequestSpec.when().jsonRequest()
             .body(LoginRequest.builder()
                     .companyCode(companyEntity.getCode())
                     .username("notExistingUser")
                     .password("<PASSWORD>")
                     .build())
             .post(PATH)
             .then()
             .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.UNAUTHORIZED));
    }

    @Test
    void testNotExistingCompany(){
         ValidatableResponse then = RequestSpec.when().jsonRequest()
            .body(LoginRequest.builder()
                    .companyCode("notExistingCompany")
                    .username(SuperuserHelper.DEFAULT_TEST_USER)
                    .password(SuperuserHelper.DEFAULT_TEST_USER)
                    .build())
            .post(PATH)
            .then()
            .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.UNAUTHORIZED));
    }
}
