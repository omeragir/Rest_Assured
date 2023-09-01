package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class P02_SpartanSpecTest extends SpartanNewTestBase {

    @Test
    public void getAllSpartans(){
        given().
                log().all()
                .accept(ContentType.JSON)
                .auth().basic("admin","admin")
        .when()
                .get("/spartans")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

    }

    @Test
    public void getAllSpartanWithReqResSpec(){
        given().
                spec(reqSpec)
        .when()
                .get("/spartans")
        .then()
                .spec(resSpec);

    }

    @Test
    public void getOneSpartanWithReqResSpec(){

        given()
                .spec(reqSpec)
                .and()
                .pathParam("id",10)
        .when().
                get("/spartans/{id}")
        .then()
                .spec(resSpec)
                .body("id",is(10));
    }

    @Test
    public void getOneSpartanAsUser(){

        given()
                .spec(reqUserSpec)
                .and()
                .pathParam("id",10)
                .when().
                get("/spartans/{id}")
                .then()
                .spec(resSpec)
                .body("id",is(10));
    }

    @Test
    public void getOneSpartanWithDynamicSpecs(){

        given()
                .spec(dynamicReqSpec("user","user"))
                .and()
                .pathParam("id",10)
                .when().
                get("/spartans/{id}")
            .then()
                .spec(dynamicResSpec(200))
                .body("id",is(10));
    }

    /**
     *  Create GET_RBAC.csv
     *    username,password,id,statusCode
     *    admin,admin,3,200
     *    editor,editor,3,200
     *    user,user,3,200
     *
     *  Create a parameterized test to check RBAC for GET method
     *
     *
     */

    @ParameterizedTest
    @CsvFileSource(resources = "/GET_RBAC.csv",numLinesToSkip = 1)
    public void getSingleSpartan_GET_RBAC(String username,String password,int id,int statusCode){
                given()
                        .spec(dynamicReqSpec(username,password))
                        .pathParam("id",id)
                .when()
                        .get("/spartans/{id}")
                .then()
                        .spec(dynamicResSpec(statusCode));
    }


    /**
     *  Create DELETE_RBAC.csv
     *   username,password,id,statusCode
     *    editor,editor,3,403
     *    user,user,3,403
     *    admin,admin,3,204
     *
     *  Create a parameterized test to check RBAC for DELETE method
     *
     *
     */

    @ParameterizedTest
    @CsvFileSource(resources = "/DELETE_RBAC.csv",numLinesToSkip = 1)
    public void deleteSingleSpartan_DELETE_RBAC(String username, String password, int id, int statusCode){

        given().spec(dynamicReqSpec(username,password))
                .pathParam("id",id)
        .when().delete("/spartans/{id}")
                .then().spec(dynamicResSpec(statusCode));

    }



}