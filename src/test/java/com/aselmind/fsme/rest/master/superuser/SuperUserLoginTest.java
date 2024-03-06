package com.aselmind.fsme.rest.master.superuser;

import com.aselmind.fsme.rest.BaseIntegrationTest;
import com.aselmind.fsme.rest.RequestSpec;
import com.aselmind.fsme.rest.ResponseSpec;
import com.aselmind.fsme.rest.utils.ControllerUtils;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

class SuperUserLoginTest extends BaseIntegrationTest {
    private final String PATH = ControllerUtils.getPath(SuperUserAccountController.class) + "/login";

    @Autowired
    private SuperuserHelper superuserHelper;

    @Test
    void testLogin200() {
        SuperUserEntity testSuperUser = superuserHelper.getOrCreateTestSuperUser();
        ValidatableResponse then = RequestSpec.when().jsonRequest()
                .body(LoginRequest.builder()
                        .username(SuperuserHelper.DEFAULT_TEST_USER)
                        .password(SuperuserHelper.DEFAULT_TEST_USER)
                        .build())
                .post(PATH)
                .then()
                .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.OK));
        LoginResult result = then.extract().as(LoginResult.class);
        assert result.getUsername().equals(testSuperUser.getUsername());
    }

    @Test
    void testWrongCredentials(){
        ValidatableResponse then = RequestSpec.when().jsonRequest()
               .body(LoginRequest.builder()
                       .username(SuperuserHelper.DEFAULT_TEST_USER)
                       .password("<PASSWORD>")
                       .build())
               .post(PATH)
               .then()
               .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.UNAUTHORIZED));
    }

    @Test
    void testNotExistingUser(){
         ValidatableResponse then = RequestSpec.when().jsonRequest()
             .body(LoginRequest.builder()
                     .username("notExistingUser")
                     .password("<PASSWORD>")
                     .build())
             .post(PATH)
             .then()
             .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.UNAUTHORIZED));
    }
}
