package com.cydeo.office_hours.week3;

import com.cydeo.pojo.Fruit;
import com.cydeo.utility.FruitTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


public class P02_SerializationWithPOJO extends FruitTestBase {


    @Test
    void createFruit() {

        Fruit requestBody=new Fruit("Fruit E",8.90);

        Response response= RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                //     .body("{\n" +  "  \"name\": \"Fruit D\",\n" + "  \"price\": 5.79\n" + "}")  //instead of that one
                //      we are able to use POJO
                .body(requestBody)
                .post("/products")
                .then()
                .statusCode(201)
                .extract().response();
        response.prettyPrint();



    }





}