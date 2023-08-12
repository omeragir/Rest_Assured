package com.cydeo.day03;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P04_HrWithResponsePath extends HrTestBase {

    @DisplayName("GET Request to countries with using Response Path")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParams("q", "{\"region_id\":2}")
                .when().get("/countries");

        //response.prettyPrint();

        //print limit
        int limit = response.path("limit");
        System.out.println("limit = " + limit);
        //print hasMore
         boolean hasMore = response.path("hasMore");
        System.out.println("hasMore = " + hasMore);
        //print second country
        System.out.println("response.path(\"items[1].country_name\") = " + response.path("items[1].country_name"));
        //print 4th element country name
        System.out.println("response.path(\"items[3].country_name\") = " + response.path("items[3].country_name"));
        //print 3rd element href
        System.out.println("response.path(\"items[2].links[0].href\") = " + response.path("items[2].links[0].href"));
        //get all countries name
        List<String> allCountryName = response.path("items.country_name");
        for (String eachCountryName : allCountryName) {
            System.out.println(eachCountryName);
        }

        //verify all regions_ids equals to 2
        List<Integer> allRegionsId = response.path("items.region_id");
        for (Integer id : allRegionsId) {
            assertEquals(2,id);
            System.out.println("id = " + id);
        }

    }

}
