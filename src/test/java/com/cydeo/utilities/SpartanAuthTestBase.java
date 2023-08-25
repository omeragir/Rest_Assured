package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanAuthTestBase {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://3.83.82.216:7000";
    }


}
