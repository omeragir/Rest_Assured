package com.cydeo.task;

import com.cydeo.utilities.ZipTestBase;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_ZipTask extends ZipTestBase {
    @DisplayName("GET all ZipCode Info")
    @Test
    public void zipCode() {
        //TASK 1
        //Given Accept application/json
        //And path zipcode is 22031
        //When I send a GET request to /us endpoint
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("post code", 22031)
                .when().get("/us/{post code}");
        response.prettyPrint();
        System.out.println("response.path(\"post code\") = " + response.path("'post code'"));


        //Then status code must be 200
        assertEquals(200,response.statusCode());
        //And content type must be application/json
        assertEquals("application/json",response.contentType());
        //And Server header is cloudflare
        assertEquals("cloudflare",response.header("Server"));
        //And Report-To header exists
        assertTrue(response.headers().hasHeaderWithName("Report-To"));
        //And body should contains following information

        //created json object
         JsonPath jsonPath = response.jsonPath();

        //post code is 22031
        assertEquals(22031,jsonPath.getInt("'post code'"));
        //country is United States
        assertEquals("United States",jsonPath.getString("country"));
        //country abbreviation is US
        assertEquals("US",jsonPath.getString("'country abbreviation'"));
        //place name is Fairfax state is Virginia
        assertEquals("Virginia",jsonPath.getString("places[0].state"));
    }
@DisplayName("GET zip code negative")
@Test
    public void test2(){
        //TASK 2
    //Given Accept application/json
    //And path zipcode is 50000
    //When I send a GET request to /us endpoint
     Response response = given().accept(ContentType.JSON)
            .and()
            .pathParam("post code", 5000)
            .when().get("/us/{post code}");

     response.prettyPrint();



    //Then status code must be 404
    assertEquals(404,response.statusCode());
    //And content type must be application/json
    assertEquals("application/json",response.contentType());
}

@Test
    public void test3(){
        //Given Accept application/json
    //And path state is va
    //And path city is fairfax
    //When I send a GET request to /us endpoint
    //Then status code must be 200
    //And content type must be application/json
    //And payload should contains following information
    //country abbreviation is US
    //country United States
    //place name Fairfax
    //each places must contains fairfax as a value each post code must start with 22
}



}
