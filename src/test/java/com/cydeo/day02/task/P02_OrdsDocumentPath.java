package com.cydeo.day02.task;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_OrdsDocumentPath {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://3.83.82.216:1000/ords/hr";
    }

    //TASK 1 :
    //- Given accept type is Json
    //- Path param value- US
    //- When users sends request to /countries
    //- Then status code is 200
    //- And Content - Type is Json
    //- And country_id is US
    //- And Country_name is United States of America
    //And Region_id is 2

    @Test
    public void pathTask() {

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("country_name", "US")
                .when().get("/countries/{country_name}");

        //- Then status code is 200
        assertEquals(200,response.statusCode());

        //- And Content - Type is Json
        assertEquals("application/json",response.contentType());

        //- And country_id is US



    }
}
