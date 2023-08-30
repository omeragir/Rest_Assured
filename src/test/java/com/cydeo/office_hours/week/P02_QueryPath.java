package com.cydeo.office_hours.week;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class P02_QueryPath extends com.cydeo.utility.FruitTestBase {
    /**
     * 1- Given accept type is Json
     * 2- Query Parameters value is
     * - start —> 1
     * - limit —> 100
     * - search —> "Fruit"
     * 3- When user sends GET request to /products
     * 4- Verify followings
     * - Status code should be 200
     * - Content Type is application/json
     * - start and limit values are matching with query params
     * - Product Names contains Fruit
     * - Get all product names
     * - Get product ids
     */

    @Test
    public void getProducts() {
        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .queryParams("start", 1)
                .and()
                .queryParams("limit", 100)
                .and()
                .queryParams("search", "Fruit").
                when()
                .get("/products").prettyPeek().
                then()
                .statusCode(200)
                .contentType("application/json")
                .body("products.name", everyItem(containsString("Fruit")))
                .extract().jsonPath();

        //2.Method  * - Product Names contains Fruit
        List<String> allFruitName = jsonPath.getList("products.name");
        for (String eachName : allFruitName) {
            System.out.println("each = " + eachName);
            assertTrue(eachName.contains("Fruit"));
        }

        List<Integer> allFruitID = jsonPath.getList("products.id");
        for (int eachID : allFruitID) {
            System.out.println("eachID = " + eachID);
        }

    }
}

