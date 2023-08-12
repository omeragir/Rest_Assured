package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_SpartanWithResponsePath extends SpartanTestBase {

    @DisplayName("Get Spartan With Response Path")
    @Test
    public void test1() {

         /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
   */

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 10)
                .when().get("api/spartans/{id}");

        //print response
        response.prettyPrint();
        //verify status code
        assertEquals(200, response.statusCode());
        //verify content type
        assertEquals("application/json", response.contentType());

        //And response payload values match the following:
        //          id is 10,
        int id = response.path("id");
        assertEquals(10,id);
        //          name is "Lorenza",
        String name = response.path("name");
        assertEquals("Lorenza",name);
        //          gender is "Female",
        String gender = response.path("gender");
        assertEquals("Female",gender);
        //          phone is 3312820936
        Long phone = response.path("phone");
        assertEquals(3_312_820_936l,phone);

        //if the path is incorrect it will return null
         String address = response.path("address");
        System.out.println("address = " + address);

    }
}
