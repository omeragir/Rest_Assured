package com.cydeo.office_hours.week3;


import com.cydeo.utilities.BookitTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class P04_AuthTestWithBearer extends BookitTestBase {


    @Test
    void getCampuse() {
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " +token)
                .accept(ContentType.JSON)
                .get("/api/campuses");

        System.out.println("response.statusCode() = " + response.statusCode());


    }
}