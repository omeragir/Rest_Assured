package com.cydeo.office_hours.week3;


import com.cydeo.utility.FruitTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class P01_SerializationWithMap extends FruitTestBase {
    int createdId;

    @Test
  public   void createFruit() {

        Map<String,Object> requestBody=new LinkedHashMap<>();

        requestBody.put("name","FruitC");
        requestBody.put("price",4.56);

     Response response= RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
          //     .body("{\n" +  "  \"name\": \"Fruit D\",\n" + "  \"price\": 5.79\n" + "}")  //instead of that one
             //      we are able to use map
            .body(requestBody)
                .post("/products")
                .then()
                .statusCode(201)
                .extract().response();
     response.prettyPrint();

        String self_link = response.path("self_link");
        String idOfString=self_link.substring(self_link.lastIndexOf("/")+1);

        int id=Integer.parseInt(idOfString);
        createdId=id;
        System.out.println("createdId = " + createdId);
    }

    //let's assume that API is not returning id hw to get created fruit in next test
    /*
    {

    "name": "FruitC",
    "price": 4.56,
    "self_link": "/shop/v2/products/455"
}
     */

/*
you will have one problem please search it problem is about getting  id=0;
1- order your tests

 */
    @Test
  public   void getFruit() {
//get method use as -----> homework
        System.out.println("createdId in get method = " + createdId);
    }
}