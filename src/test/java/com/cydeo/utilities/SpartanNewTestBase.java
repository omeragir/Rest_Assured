package com.cydeo.utilities;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class SpartanNewTestBase {

    public static RequestSpecification reqSpec;
    public static ResponseSpecification resSpec;
    public static RequestSpecification reqUserSpec;

    @BeforeAll
    public static void init(){

        baseURI = "http://3.83.82.216";
        port = 7000;
        basePath= "/api";

        //baseUri+port+basePath
        //http://54.86.2.212:7000/api

         reqSpec = given().
                log().all()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin");

        reqUserSpec = given().
                log().all()
                .accept(ContentType.JSON)
                .auth().basic("user", "user");


         resSpec =
                expect().statusCode(200)
                        .contentType(ContentType.JSON);

    }

    //Create dynamic method which is accepting username and password as a parameter and returning
    //request specification dynamicReqSpec()

    public static RequestSpecification dynamicReqSpec(String username,String password){

        return given().
                log().all()
                .accept(ContentType.JSON)
                .auth().basic(username, password);
    }

    //Create dynamic method which has parameter for status code, and returning ResponseSpecification
    // dynamicResSpec()

    public static ResponseSpecification dynamicResSpec(int statusCode){
        return expect().statusCode(statusCode)
                .contentType(ContentType.JSON);

    }
}