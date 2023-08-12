package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    @DisplayName("GET All Spartan")
    @Test
    public void allSpartanPath(){

         Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        // response.prettyPrint();

         //get me first spartan id
        int firstId = response.path("[0].id");
        System.out.println("firstId = " + firstId);

        int idFirst = response.path("id[0]");
        System.out.println("idFirst = " + idFirst);

        //get me first name
        System.out.println("response.path(\"[0].name\") = " + response.path("[0].name"));

        //get me last spartan
        //name[-1]--> refers last element of the name list
        System.out.println("response.path(\"name[-1]\") = " + response.path("name[-1]"));

        //get me second spartan name from the last
        System.out.println("response.path(\"name[-2]\") = " + response.path("name[-2]"));
        
        //get me all spartan name
        List<String> allName = response.path("name");

        //how to print each
        for (String eachName : allName) {
            System.out.println(eachName);
        }
    }
}
