package com.cydeo.task;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_EmployeesTask {


    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://3.83.82.216:1000/ords/hr";
    }
    //Task 1 :
    //- Given accept type is Json
    //- When users sends request to /countries/US
    //- Then status code is 200
    //- And Content - Type is application/json
    //- And response contains United States of America

    @Test
    public void employeeTask() {
        Response response = given()
                .accept(ContentType.JSON)
                .when().get("/countries/US");

        //response.prettyPrint();

        //status code
        assertEquals(200, response.getStatusCode());

        //contains United States

        assertTrue(response.body().asString().contains("United States of America"));


    }

    //Task 2 :
    //
    //- Given accept type is Json
    //- When users sends request to /employees/1
    //- Then status code is 404
    @Test
    public void statusCode() {
        Response response = given()
                .accept(ContentType.JSON)
                .when().get("/employees/1");

        assertEquals(404, response.getStatusCode());
    }

    //Task 3 :
    //- Given accept type is Json
    //- When users sends request to /regions/1
    //- Then status code is 200
    //- And Content - Type is application/json
    //- And response contains Europe
    //- And header should contain Date
    //- And Transfer-Encoding should be chunked
    @Test
    public void regionsTask() {
        Response response = given()
                .accept(ContentType.JSON)
                .when().get("/regions/1");
        //response.prettyPrint();

        //status code
        assertEquals(200, response.statusCode());

        //And Content - Type is application/json
        assertEquals("application/json", response.contentType());
        assertEquals(ContentType.JSON.toString(), response.header("Content-type"));


        //And response contains Europe
        assertTrue(response.body().asString().contains("Europe"));

        //And header should contain Date
        boolean date = response.headers().hasHeaderWithName("Date");
        assertTrue(date);

        //And Transfer-Encoding should be chunked
        assertEquals("chunked", response.header("Transfer-Encoding"));


    }


}
