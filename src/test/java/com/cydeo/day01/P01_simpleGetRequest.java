package com.cydeo.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_simpleGetRequest {

    String url="http://3.83.82.216:8000/api/spartans";
    /*
    When user send request to api/spartans endpoint
    Then user should be able to see status code is 200
    And Print out response body into screen
     */
    @Test
    public void simpleGetRequest(){
         Response response = RestAssured.get(url);

         //both same no difference,they get the response status code
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        //verify that status code is 200
         int actualStatusCode = response.statusCode();

         //Assert that is 200
        Assertions.assertEquals(200,actualStatusCode);

        //How to print json response body on console
        response.prettyPrint();


    }
}
