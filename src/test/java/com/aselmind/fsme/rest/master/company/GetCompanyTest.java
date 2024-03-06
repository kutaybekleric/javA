package com.aselmind.fsme.rest.master.company;

import com.aselmind.fsme.rest.BaseIntegrationTest;
import com.aselmind.fsme.rest.RequestSpec;
import com.aselmind.fsme.rest.ResponseSpec;
import com.aselmind.fsme.rest.company.user.CompanyUserHelper;
import com.aselmind.fsme.rest.master.superuser.SuperuserHelper;
import com.aselmind.fsme.rest.utils.ControllerUtils;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.event.annotation.BeforeTestClass;

class GetCompanyTest extends BaseIntegrationTest {
    private final String PATH = ControllerUtils.getPath(CompanyController.class) + "/{id}";

    @Autowired
    private SuperuserHelper superuserHelper;

    @Autowired
    private CompanyUserHelper companyUserHelper;

    @Autowired
    private CompanyHelper companyHelper;

    CompanyEntity testCompany;

    @BeforeEach
    void beforeAll(){
        testCompany = companyHelper.getOrCreateTestCompany();
    }

    @Test
    void testGet200(){
        ValidatableResponse then = RequestSpec.when().auth(superuserHelper.getOrCreateTestSuperUser()).jsonRequest()
                .get(PATH,testCompany.getId())
                .then()
                .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.OK));
    }

    @Test
    void testGet403(){
        ValidatableResponse then = RequestSpec.when().auth(companyHelper.getOrCreateTestCompany(),companyUserHelper.getOrCreateTestUserOwner(companyHelper.getOrCreateTestCompany())).jsonRequest()
                .get(PATH,testCompany.getId())
                .then()
                .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.FORBIDDEN));
    }

    @Test
    void testGet401(){
        ValidatableResponse then = RequestSpec.when().jsonRequest()
                .get(PATH,testCompany.getId())
                .then()
                .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.UNAUTHORIZED));
    }
}
