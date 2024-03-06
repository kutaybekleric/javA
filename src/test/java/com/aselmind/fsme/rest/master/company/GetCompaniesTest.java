package com.aselmind.fsme.rest.master.company;

import com.aselmind.fsme.rest.BaseIntegrationTest;
import com.aselmind.fsme.rest.RequestSpec;
import com.aselmind.fsme.rest.ResponseSpec;
import com.aselmind.fsme.rest.company.user.CompanyUserHelper;
import com.aselmind.fsme.rest.master.superuser.SuperuserHelper;
import com.aselmind.fsme.rest.utils.ControllerUtils;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;

class GetCompaniesTest extends BaseIntegrationTest {
    private final String PATH = ControllerUtils.getPath(CompanyController.class);

    @Autowired
    private SuperuserHelper superuserHelper;

    @Autowired
    private CompanyUserHelper companyUserHelper;

    @Autowired
    private CompanyHelper companyHelper;

    @Test
    void testGet200(){
        ValidatableResponse then = RequestSpec.when().auth(superuserHelper.getOrCreateTestSuperUser()).jsonRequest()
                .get(PATH)
                .then()
                .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.OK));
    }

    @Test
    void testGet403(){
        ValidatableResponse then = RequestSpec.when().auth(companyHelper.getOrCreateTestCompany(),companyUserHelper.getOrCreateTestUserOwner(companyHelper.getOrCreateTestCompany())).jsonRequest()
                .get(PATH)
                .then()
                .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.FORBIDDEN));
    }

    @Test
    void testGet401(){
        ValidatableResponse then = RequestSpec.when().jsonRequest()
                .get(PATH)
                .then()
                .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.UNAUTHORIZED));
    }
}
