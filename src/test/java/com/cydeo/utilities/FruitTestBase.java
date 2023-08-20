package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public abstract class FruitTestBase {
    @BeforeAll
    public static void init() {
        baseURI = "https://api.predic8.de/shop/v2";
    }

    @AfterAll
    public static void destroy() {
       reset();
    }

}
