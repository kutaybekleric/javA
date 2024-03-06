package com.aselmind.fsme.rest.master.company;

import com.aselmind.fsme.rest.BaseIntegrationTest;
import com.aselmind.fsme.rest.RequestSpec;
import com.aselmind.fsme.rest.ResponseSpec;
import com.aselmind.fsme.rest.company.user.CompanyUserHelper;
import com.aselmind.fsme.rest.config.exception.ErrorBody;
import com.aselmind.fsme.rest.config.exception.ErrorResponse;
import com.aselmind.fsme.rest.config.exception.ResourceNotFoundException;
import com.aselmind.fsme.rest.master.superuser.SuperuserHelper;
import com.aselmind.fsme.rest.utils.ControllerUtils;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;

class UpdateCompanyTest extends BaseIntegrationTest {
    private final String PATH = ControllerUtils.getPath(CompanyController.class) + "/{id}";

    @Autowired
    private SuperuserHelper superuserHelper;

    @Autowired
    private CompanyUserHelper companyUserHelper;

    @Autowired
    private CompanyHelper companyHelper;

    private CompanyEntity companyEntity;

    @BeforeEach
    void beforeEach(){
        companyEntity = companyHelper.createRandomCompany();
    }

    @Test
    void testUpdate204() {
        UpdateCompanyRequest updateCompanyRequest = buildUpdateCompanyRequest();
        ValidatableResponse then = RequestSpec.when().auth(superuserHelper.getOrCreateTestSuperUser()).jsonRequest()
                .body(updateCompanyRequest)
                .put(PATH,companyEntity.getId())
                .then()
                .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.NO_CONTENT));
    }

    @Test
    void testUpdate400(){
        UpdateCompanyRequest updateCompanyRequest = UpdateCompanyRequest.builder().build();
        ValidatableResponse then = RequestSpec.when().auth(superuserHelper.getOrCreateTestSuperUser()).jsonRequest()
              .body(updateCompanyRequest)
              .put(PATH,companyEntity.getId())
              .then()
              .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.BAD_REQUEST));
    }

    @Test
    void testUpdateNotFound400() {
        UpdateCompanyRequest updateCompanyRequest = buildUpdateCompanyRequest();
        ValidatableResponse then = RequestSpec.when().auth(superuserHelper.getOrCreateTestSuperUser()).jsonRequest()
               .body(updateCompanyRequest)
               .put(PATH,"notExistigId")
               .then()
               .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.BAD_REQUEST));
        ErrorResponse errorResponse = then.extract().as(ErrorResponse.class);
        //errorResponse check
        List<ErrorBody> errors = errorResponse.getErrors();
        assert errors.size() == 1;
        ErrorBody errorBody = errors.get(0);
        assert errorBody.getStatus() == HttpStatus.BAD_REQUEST.value();
        assert errorBody.getType().equals(ResourceNotFoundException.class.getSimpleName());
    }

    @Test
    void testUpdate403() {
        UpdateCompanyRequest updateCompanyRequest = buildUpdateCompanyRequest();
        ValidatableResponse then = RequestSpec.when().auth(companyHelper.getOrCreateTestCompany(),companyUserHelper.getOrCreateTestUserOwner(companyHelper.getOrCreateTestCompany())).jsonRequest()
              .body(updateCompanyRequest)
              .put(PATH,companyEntity.getId())
              .then()
              .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.FORBIDDEN));
    }

    @Test
    void testUpdate401() {
        UpdateCompanyRequest updateCompanyRequest = buildUpdateCompanyRequest();
        ValidatableResponse then = RequestSpec.when().jsonRequest()
             .body(updateCompanyRequest)
             .put(PATH,companyEntity.getId())
             .then()
             .log().all();

        then.spec(ResponseSpec.isHttpStatus(HttpStatus.UNAUTHORIZED));
    }

    private static UpdateCompanyRequest buildUpdateCompanyRequest() {
        String ts = String.valueOf(System.currentTimeMillis());
        String title = "t_" + ts;

        return UpdateCompanyRequest.builder()
                .title(title)
                .build();
    }
}
