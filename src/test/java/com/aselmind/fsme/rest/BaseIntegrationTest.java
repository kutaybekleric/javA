package com.aselmind.fsme.rest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Locale;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public abstract class BaseIntegrationTest {

	@BeforeAll
	public static void beforeAllBaseIntegrationTest(){
		Locale.setDefault(Locale.ENGLISH);
		RestAssured.basePath = "/api/v1";
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}


}
