package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_SpartanWithParameters extends SpartanTestBase {

        /*
        Given accept type is JSON
        And ID parameter value is 24
        When user sends GET request to /api/spartans/{id}
        Then response status code should  be 200
        And response content-type:application/json
        And "Julio" should be in response payload(body)
         */

    @DisplayName("GET Spartan /api/spartans/{id} path param with 24")
    @Test
    public void pathParamTask() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 24)
                .when().get("/api/spartans/{id}");

        response.prettyPrint();
        //Then response status code should  be 200
        assertEquals(200, response.statusCode());
        //And response content-type:application/json
        assertEquals("application/json", response.contentType());
        //And "Julio" should be in response payload(body)
        assertTrue(response.body().asString().contains("Julio"));

    }

    /*
    Given accept type is JSON
    And ID parameter value is 500
    When user sends GET request to /api/spartans/{id}
    Then response status code should  be 404
    And response content-type:application/json
    And "Not Found" message should be in response payload(body)
     */
    @DisplayName("GET Spartan /api/spartans/{id} path param with invalid ID")
    @Test
    public void notFoundTask() {

        Response response = given()
                .and()
                .accept(ContentType.JSON)
                .pathParam("id", 500)
                .when().get("/api/spartans/{id}");

        //print response
        response.prettyPrint();

        // Then response status code should  be 404
        assertEquals(404, response.statusCode());

        //Same with above
        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());

        //And response content-type:application/json
        assertEquals("application/json", response.contentType());

        // And "Not Found" message should be in response payload(body)
        assertTrue(response.body().asString().contains("Not Found"));


    }

    /*
    Given accept type is JSON
    And query parameters value are:
    gender | Female
    nameContains | e
    When user sends GET request to /api/spartans/search
    Then response status code should  be 200
    And response content-type:application/json
    And "Female" should be in response payload
    And "Janetta"  should be in response payload(body)
     */
    @DisplayName("GET Spartan /api/spartans/search with query param")
    @Test
    public void queryParamTask() {

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("gender", "Female")
                .and()
                .queryParam("nameContains", "e")
                .when().get("/api/spartans/search");

        //print response
        response.prettyPrint();

        //Then response status code should  be 200
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        //    And response content-type:application/json
        assertEquals("application/json", response.contentType());

        //    And "Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));

        //    And "Janette"  should be in response payload(body)
        assertTrue(response.body().asString().contains("Janette"));

        /*
        We are just doing exercise to verify something in Response.This is not the proper way to verify
        all Spartan genders are Female or name is field is Janette.We will learn different method to get specific data.
         */
    }

    @DisplayName("GET Spartan /api/spartans/search with query param")
    @Test
    public void queryParamTask1() {

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("gender", "Female");
        queryMap.put("nameContains", "e");

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParams(queryMap)
                .when().get("/api/spartans/search");

        //print response
        response.prettyPrint();

        //Then response status code should  be 200
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        //    And response content-type:application/json
        assertEquals("application/json", response.contentType());

        //    And "Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));

        //    And "Janette"  should be in response payload(body)
        assertTrue(response.body().asString().contains("Janette"));

        /*
        We are just doing exercise to verify something in Response.This is not the proper way to verify
        all Spartan genders are Female or name is field is Janette.We will learn different method to get specific data.
         */
    }

}
