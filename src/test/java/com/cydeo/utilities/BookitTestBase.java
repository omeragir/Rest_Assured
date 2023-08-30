package com.cydeo.utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.reset;

public  class BookitTestBase {
protected String token;

    @BeforeAll
    public static void init(){

        baseURI="https://api.qa.bookit.cydeo.com";

    }

    @BeforeEach
    public void getToken(){


        String email="lfinnisz@yolasite.com";
        String password="lissiefinnis";

         token =    RestAssured
                .given()
                // .accept(ContentType.JSON)
                .queryParam("email",email)
                .queryParam("password",password)
                .get("/sign")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("accessToken");




    }

    @AfterAll
    public static void destroy(){
        reset();
    }
}