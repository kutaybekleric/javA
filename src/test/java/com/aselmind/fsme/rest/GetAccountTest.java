package com.aselmind.fsme.rest;

import com.aselmind.fsme.rest.company.user.CompanyUserHelper;
import com.aselmind.fsme.rest.master.company.CompanyController;
import com.aselmind.fsme.rest.master.company.CompanyHelper;
import com.aselmind.fsme.rest.master.superuser.SuperuserHelper;
import com.aselmind.fsme.rest.utils.ControllerUtils;
import com.aselmind.fsme.rest.utils.CurrentUser;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class GetAccountTest extends BaseIntegrationTest {
    private final String PATH = ControllerUtils.getPath(AccountController.class);

    @Autowired
    private SuperuserHelper superuserHelper;

    @Autowired
    private CompanyUserHelper companyUserHelper;

    @Autowired
    private CompanyHelper companyHelper;

    @Test
    void testGet200AsSuperUser(){
        ValidatableResponse then = RequestSpec.when().auth(superuserHelper.getOrCreateTestSuperUser()).jsonRequest()
             .get(PATH)
             .then()
             .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.OK));
        CurrentUser currentUser = then.extract().as(CurrentUser.class);
        assert currentUser.getCompanyEntity() == null;
    }
}
