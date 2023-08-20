package com.cydeo.office_hours.week;

import com.cydeo.utilities.FruitTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class PathParam extends FruitTestBase {

    /**
     *1- Given accept type is Json
     *2- Path Parameters value is
     *     - id —> 4
     *3- When user sends GET request to /products/{id}
     *4- Verify followings
     *     - Status code should be 200
     *     - Content Type is application/json
     *     - Print response
     *     - id is 4
     *     - Name is "Coconut"
     *     - Vendor name is "True Fruits Inc."
     *
     */
    @Test
    public void getSingleProduct() {


        Response response = given().log().uri().accept(ContentType.JSON) // send me data in JSON format
                .pathParam("id", 4).
                when().get("/products/{id}").prettyPeek();

        //    *     - Status code should be 200
        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals(200,response.getStatusCode());
        Assertions.assertEquals(HttpStatus.SC_OK,response.getStatusCode());
        Assertions.assertEquals(HttpStatus.SC_OK,response.statusCode());


        //     *     - Content Type is application/json
        Assertions.assertEquals("application/json",response.contentType());
        Assertions.assertEquals("application/json",response.getContentType());
        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());
        Assertions.assertEquals(ContentType.JSON.toString(),response.getContentType());


        //     *     - id is 4
        int id = response.path("id");
        Assertions.assertEquals(4,id);

        Assertions.assertEquals(4, (Integer) response.path("id"));


        //     *     - Name is "Coconut"
        String name = response.path("name");
        Assertions.assertEquals("Coconut",name);

        //     *     - Vendor name is "True Fruits Inc."
        String vName = response.path("vendors[0].name");
        System.out.println(vName);
        Assertions.assertEquals("True Fruits Inc.",vName);
    }



    @Test
    public void getSingleProductJsonPath() {


        Response response = given().log().uri().accept(ContentType.JSON) // send me data in JSON format
                .pathParam("id", 4).
                when().get("/products/{id}").prettyPeek();

        JsonPath jp = response.jsonPath();

        //     *     - Status code should be 200
        Assertions.assertEquals(200,response.statusCode());

        //     *     - Content Type is application/json
        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());

        //     *     - id is 4
        int id = jp.getInt("id");
        Assertions.assertEquals(4,id);
        Assertions.assertEquals(4,jp.getInt("id"));

        //     *     - Name is "Coconut"
        Assertions.assertEquals("Coconut",jp.getString("name"));

        //     *     - Vendor name is "True Fruits Inc."
        Assertions.assertEquals("True Fruits Inc.",jp.getString("vendors[0].name"));

    }


    @Test
    public void getSingleProductwithHamCrest() {


        given().log().uri().accept(ContentType.JSON) // send me data in JSON format
                .pathParam("id", 4).
                when().get("/products/{id}").prettyPeek().
                then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", is(4))
                .body("name",is("Coconut"))
                .body("vendors[0].name",is("True Fruits Inc."));

    }


    @Test
    public void getSingleProductwithHamCrestPlusJsonPath() {


        JsonPath jsonPath = getResponse("/products/{id}", 4);

        Assertions.assertEquals(4,jsonPath.getInt("id"));

        Assertions.assertEquals("Coconut",jsonPath.getString("name"));

        Assertions.assertEquals("True Fruits Inc.",jsonPath.getString("vendors[0].name"));

    }


    public static JsonPath getResponse(String endpoint,int pathParam){

        return  given().log().uri().accept(ContentType.JSON) // send me data in JSON format
                .pathParam("id", pathParam).
                when().get(endpoint).prettyPeek().
                then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

    }
}
