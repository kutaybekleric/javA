package com.aselmind.fsme.rest;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

@UtilityClass
public class ResponseSpec {

    public ResponseSpecification isHttpStatus(HttpStatus httpStatus){
        return new ResponseSpecBuilder().expectStatusCode(httpStatus.value()).build();
    }
}
