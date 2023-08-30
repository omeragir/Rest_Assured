package com.cydeo.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.reset;

public class SpartanTestBaseAuth {


    @BeforeAll
    public static void init(){

        baseURI="http://44.201.221.73:7000";

    }

    @AfterAll
    public static void destroy(){
        reset(); // RestAssured.reset();
        // It resets your BaseURI/BasePath etc to DEFAULT values from Rest Assured
    }
}