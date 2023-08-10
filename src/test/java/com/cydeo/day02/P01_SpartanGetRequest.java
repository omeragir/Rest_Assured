package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SpartanGetRequest {

    String url = "http://3.83.82.216:8000";

    //Task 1 :
    //- Given content type is application/json
    //- When users sends GET request /api/spartans endpoint
    //- Then status code is 200
    //- And Content - Type is application/json


    @Test
    public void getAllSpartans() {
        Response response = RestAssured.given()
                .accept(ContentType.JSON)  //hey api please send me json response
                .when()
                .get(url + "/api/spartans");

        //print the response body
        //response.prettyPrint();

        //how to get status code
        int actualStatusCode = response.statusCode();
        Assertions.assertEquals(200, actualStatusCode);

        //how to get response content type header
        String actualContentType = response.contentType();
        System.out.println("actualContentType = " + actualContentType);

        //assert the content type
        Assertions.assertEquals("application/json", actualContentType);

        //how to get connections header value?
        //If we want to get any response header value,we can use header("headerName")
        //method from response object. It will return header value as string
        System.out.println("response.header(\"Content-Type\") = " + response.header("Content-type"));
        System.out.println("response.header(\"Connection\") = " + response.header("Connection"));
        System.out.println("response.header(\"date\") = " + response.header("date"));

        //how to verify header exist?
        //hasHeaderWithName method help us to verify header exists or not
        //it is useful for dynamic header values like Date,we are only verifying header exist or not,not checking the value

        boolean isDate = response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(isDate);

    }

    //Task 2 :
    //- Given content type is application/json
    //- When users sends GET request /api/spartans/3  endpoint
    //- Then status code is 200
    //- And Content - Type is application/json
    //- And response body needs to contain Fidole


    @Test
    public void getSpartanID() {
        Response response = RestAssured.given()
                .accept(ContentType.JSON)  //hey api please send me json response
                .when()
                .get(url + "/api/spartans/3");

        //status code
        int actualStatusCode = response.statusCode();
        Assertions.assertEquals(200, actualStatusCode);
        System.out.println("actualStatusCode = " + actualStatusCode);

        //Content Type
        //1 options
        String actualContentType = response.contentType();
        Assertions.assertEquals("application/json", actualContentType);
        //2 options
        Assertions.assertEquals(ContentType.JSON.toString(), response.header("Content-type"));
        //3 options
        Assertions.assertEquals("application/json", response.getContentType());
        System.out.println("actualContentType = " + actualContentType);


        //Verify body contains "Fidole"
        response.prettyPrint();
        response.body().asString().contains("Fidole");
        Assertions.assertTrue(response.body().asString().contains("Fidole"));
        /*
        This is not a good way to make assertion.In this way we are just converting response to String with
        the help of String contains we are just looking into Response.But we should be able to get json "name"
        key value then verify that one is equal to "Fidole"
         */


    }

    //Task -3
        /*
        Given no header providers
        When Users send GET request to /api/hello
        Then response status code should be 200
        And Content type header should be "text/plain;charset=UTF-8"
        And header should contain Date
        And Content-Length should be 17
        And body should be "Hello from Sparta
         */
    @Test
    public void helloSpartan() {
        Response response = RestAssured.when().get(url + "/api/hello");
        //print result on the console
        response.prettyPrint();

        //verify status code
        int actualStatusCode = response.statusCode();
        Assertions.assertEquals(200, actualStatusCode);

        //Content type
        Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());

        //And header should contain Date
        boolean isDate = response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(isDate);

        // And Content-Length should be 17
        Assertions.assertEquals("17",response.header("Content-Length"));

        // And body should be "Hello from Sparta"
        Assertions.assertTrue(response.body().asString().equals("Hello from Sparta"));





    }
}
