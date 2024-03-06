package com.aselmind.fsme.rest.master.company;

import com.aselmind.fsme.rest.BaseIntegrationTest;
import com.aselmind.fsme.rest.RequestSpec;
import com.aselmind.fsme.rest.ResponseSpec;
import com.aselmind.fsme.rest.company.user.CompanyUserHelper;
import com.aselmind.fsme.rest.master.superuser.SuperuserHelper;
import com.aselmind.fsme.rest.utils.ControllerUtils;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

class PostCompanyTest extends BaseIntegrationTest {
    private final String PATH = ControllerUtils.getPath(CompanyController.class);
    @Autowired
    private SuperuserHelper superuserHelper;
    @Autowired
    private CompanyUserHelper companyUserHelper;
    @Autowired
    private CompanyHelper companyHelper;

    @Test
    void testPost201() {
        CreateCompanyRequest request = buildCreateCompanyRequest();

        ValidatableResponse then = RequestSpec.when().auth(superuserHelper.getOrCreateTestSuperUser()).jsonRequest()
                .body(request)
                .post(PATH)
                .then()
                .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.CREATED));
        CompanyEntity response = then.extract().body().as(CompanyEntity.class);
        Assertions.assertEquals(request.getTitle(), response.getTitle());
        Assertions.assertEquals(request.getCode(), response.getCode());
        Assertions.assertTrue(response.getDbName().endsWith(request.getDbNameSuffix()));
    }

    @Test
    void testPost400() {
        String ts = String.valueOf(System.currentTimeMillis());
        String title = "t_" + ts;
        String code = "c_" + ts;

        CreateCompanyRequest request = CreateCompanyRequest.builder()
                .title(title)
                .code(code)
                .build();

        ValidatableResponse then = RequestSpec.when().auth(superuserHelper.getOrCreateTestSuperUser()).jsonRequest()
                .body(request)
                .post(PATH)
                .then()
                .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.BAD_REQUEST));
    }

    @Test
    void testPost401() {
        CreateCompanyRequest request = buildCreateCompanyRequest();

        ValidatableResponse then = RequestSpec.when().jsonRequest()
                .body(request)
                .post(PATH)
                .then()
                .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.UNAUTHORIZED));
    }

    @Test
    void testPost403() {
        CreateCompanyRequest request = buildCreateCompanyRequest();

        ValidatableResponse then = RequestSpec.when().auth(companyHelper.getOrCreateTestCompany(),companyUserHelper.getOrCreateTestUserOwner(companyHelper.getOrCreateTestCompany())).jsonRequest()
                .body(request)
                .post(PATH)
                .then()
                .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.FORBIDDEN));
    }

    private static CreateCompanyRequest buildCreateCompanyRequest() {
        String ts = String.valueOf(System.currentTimeMillis());
        String title = "t_" + ts;
        String code = "c_" + ts;
        String dbName = "d_" + ts;

        return CreateCompanyRequest.builder()
                .title(title)
                .code(code)
                .dbNameSuffix(dbName)
                .build();
    }
}
