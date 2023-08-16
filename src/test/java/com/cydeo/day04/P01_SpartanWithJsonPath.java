package com.cydeo.day04;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_SpartanWithJsonPath extends SpartanTestBase {

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
        //          name is "Lorenza",
        //         gender is "Female",
        //          phone is 3312820936


        System.out.println("response.path(\"id\") = " + response.path("id").toString());

        //we save our response as JSONPATH object
         JsonPath jsonPath = response.jsonPath();
         int id = jsonPath.getInt("id");
         String name = jsonPath.getString("name");
         String gender = jsonPath.getString("gender");
         long phone = jsonPath.getLong("phone");

         assertEquals(10,id);
         assertEquals("Lorenza",name);


    }

}
