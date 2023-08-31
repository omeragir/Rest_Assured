package com.cydeo.day11;

import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

public class P05_CsvFileSourceTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/math.csv",numLinesToSkip = 1)
    public void test1(int n1,int n2,int total){
        System.out.println("n1 = " + n1);
        System.out.println("n2 = " + n2);
        System.out.println("total = " + total);
    }

    /**
     *    // Write a parameterized test for this request
     *     // Get the data from csv source called as --> zipcode.csv
     *     // state , city , numberOfPlace
     *     // GET https://api.zippopotam.us/us/{state}/{city}
     *     // After getting response numberOfPlaces needs to be same
     *
     *     state , city , numberOfPlace
     *     NY,New York,166
     *     CO,Denver,76
     *     VA,Fairfax,10
     *     MA,Boston,56
     *     MD,Annapolis,9
     */

    @ParameterizedTest
    @CsvFileSource(resources = "/zipcode.csv",numLinesToSkip = 1)
    public void test2(String state,String city,int placeNumber){
        System.out.println("state = " + state);
        System.out.println("city = " + city);
        System.out.println("placeNumber = " + placeNumber);

        given()
                .accept(ContentType.JSON)
                .baseUri("https://api.zippopotam.us")
                .pathParam("state",state)
                .pathParam("city",city)
                .when()
                .get("/us/{state}/{city}")
        .then()
                .statusCode(200)
                .body("places",hasSize(placeNumber));

    }

}