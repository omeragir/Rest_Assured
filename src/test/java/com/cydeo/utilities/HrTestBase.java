package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class HrTestBase {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://3.83.82.216:1000/ords/hr";
    }

}
