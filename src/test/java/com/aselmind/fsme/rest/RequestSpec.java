package com.aselmind.fsme.rest;

import com.aselmind.fsme.rest.config.security.JwtUtils;
import com.aselmind.fsme.rest.master.company.CompanyEntity;
import com.aselmind.fsme.rest.master.superuser.SuperUserEntity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.security.core.userdetails.UserDetails;


public class RequestSpec {
    protected RequestSpecification spec;

    protected RequestSpec() {
        this.spec = RestAssured.with();
    }

    public static RequestSpec when() {
        return new RequestSpec();
    }

    public RequestSpec auth(UserDetails entity){
        return auth(null,entity);
    }

    public RequestSpec auth(CompanyEntity company, UserDetails entity){
        String token = JwtUtils.generateToken(company,entity.getUsername(),false);
        spec = spec.header("Authorization","Bearer " + token);
        return this;
    }

    public RequestSpecification jsonRequest() {
        spec = spec.contentType(ContentType.JSON);
        spec = spec.accept(ContentType.JSON);
        return spec;
    }

    public RequestSpecification request(){
        return spec;
    }


}
