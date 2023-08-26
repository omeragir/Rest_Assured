package com.cydeo.day07;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P04_SpartanFlow extends SpartanTestBase {
    /*

   Create a Spartan Flow to run below testCases dynamically

  - POST     /api/spartans
           Create a spartan Map,Spartan class
               name = "API Flow POST"
               gender="Male"
               phone=1231231231l

           - verify status code 201
           - message is "A Spartan is Born!"
           - take spartanID from response and save as a global variable


   - GET  Spartan with spartanID     /api/spartans/{id}


            - verify status code 200
            - verify name is API Flow POST

   - PUT  Spartan with spartanID    /api/spartans/{id}

            Create a spartan Map
               name = "API PUT Flow"
               gender="Female"
               phone=3213213213l

            - verify status code 204

   - GET  Spartan with spartanID     /api/spartans/{id}


            - verify status code 200
            - verify name is API PUT Flow

   - DELETE  Spartan with spartanID   /api/spartans/{id}


            - verify status code 204

    - GET  Spartan with spartanID   /api/spartans/{id}


            - verify status code 404


   Challenges
      Create @Test annotated method for each Request
      Put them in order to execute as expected
                   - Use your googling skills
                   - How to run Junit5 Tests in order  ?



    */
    @DisplayName("Post spartan with Map body")
    @Test
    public void test1() {
        /*
         Create a Spartan Flow to run below testCases dynamically

   - POST     /api/spartans
            Create a spartan Map,Spartan class
                name = "API Flow POST"
                gender="Male"
                phone=1231231231l

            - verify status code 201
            - message is "A Spartan is Born!"
            - take spartanID from response and save as a global variable
         */
        Map<String, Object> requestMapBody = new LinkedHashMap<>();
        requestMapBody.put("name", "API Flow POST");
        requestMapBody.put("gender", "Male");
        requestMapBody.put("phone", 1231231231l);

        System.out.println("requestMapBody = " + requestMapBody);

        Response response =
                given().
                 log().ifValidationFails()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(requestMapBody).
        when()
                .post("/api/spartans").prettyPeek().
        then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success",is("A Spartan is Born!"))
                .extract().response();

         JsonPath jsonPath = response.jsonPath();


         assertEquals("API Flow POST",jsonPath.getString("data.name"));
         assertEquals("Male",jsonPath.getString("data.gender"));
         assertEquals(1231231231l,jsonPath.getLong("data.phone"));
    }
}
