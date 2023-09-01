package com.cydeo.day12;


import com.cydeo.utilities.BookItUtils;
import com.cydeo.utilities.BookitTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P03_BookitSpecTest extends BookitTestBase {

    /**
     * Send a request /api/users/me endpoint as teacher
     * verify status code 200
     * content Type will be ContentType.JSON
     *
     */

    @Test
    public void test1(){
        given().
                log().all()
                .accept(ContentType.JSON)
                .header("Authorization", BookItUtils.getTokenByRole("teacher"))
        .when()
                .get("/api/users/me")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

    }

    @Test
    public void test2(){
        given().
                spec(BookItUtils.getReqSpec("teacher"))
        .when()
                .get("/api/users/me")
        .then()
                .spec(BookItUtils.getResSpec(200));

    }

    //Create one req spec for bookitUtils which accepts role
    //Create one res spec for status code and json verification

}