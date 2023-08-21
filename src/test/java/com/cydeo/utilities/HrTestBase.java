package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.reset;

public abstract class HrTestBase {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://3.83.82.216:1000/ords/hr";
    }
    @AfterAll
    public static void destroy() {
        reset();
    }

}
