package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_NegativeSpartanTest {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://3.83.82.216:8000";
    }

    //Task 1 :
    //- Given content type is application/json
    //- When users sends GET request /api/spartans endpoint
    //- Then status code is 200
    @DisplayName("GET ALL SPARTANS")
    @Test
    public void getAllSpartan() {
        Response response = given()
                .accept(ContentType.JSON)  //hey api please send me json response
                .when()
                .get("/api/spartans");
        assertEquals(200, response.statusCode());
        response.prettyPrint();

    }

    //Task-2
    //- Given content type is application/xml
    //- When users sends GET request /api/spartans/10 endpoint
    //- Then status code must be 406
    //-And Content type must be "application/xml;charset=UTF-8"
    @DisplayName("Accept,application/xml - 406")
    @Test
    public void xmlTest() {
        Response response = given().accept(ContentType.XML)
                .when().get("/api/spartans/10");

        //verify status code is 406
        assertEquals(406, response.statusCode());

        //-And Content type must be "application/xml;charset=UTF-8"
        assertEquals("application/xml;charset=UTF-8", response.contentType());


    }
}
