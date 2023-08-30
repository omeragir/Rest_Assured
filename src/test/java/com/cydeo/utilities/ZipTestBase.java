package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class ZipTestBase {
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "https://api.zippopotam.us";
    }

}
