package com.cydeo.office_hours.week;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class P03_HamcrestHR extends HrTestBase {

    /**
     *
     *        Given accept type is Json
     *        And parameters: q={"region_id":2}
     *        When users sends a GET request to "/countries"
     *        Then status code is 200
     *        And Content type is application/json
     *        And Date header is not null
     *        Verify
     *            - count is 5
     *            - hasMore is false
     *            - first country id is AR
     *            - country names have Canada
     *            - country names have Canada,Mexico
     *            - total country size is 5
     *            - each country has country_id
     *            - each country region_id is 2
     *       - Print country names
     *
     *
     *
     */

    @Test
    public void getCountries() {
         JsonPath jsonPath = given().log().uri().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}").
                when().get("/countries").prettyPeek().
                then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Date", is(notNullValue()))
                .body("count", is(5))
                .body("hasMore", is(false))
                .body("items[0].country_id", is("AR"))
                .body("items.country_name", hasItem("Canada"))
                .body("items.country_name", hasItems("Canada", "Mexico"))
                .body("items.country_id", everyItem(notNullValue()))
                .body("items.region_id", everyItem(is(2)))
                .body("items", hasSize(5))
                .extract().jsonPath();

         List<String>allCountries=jsonPath.getList("items.country_name");
        System.out.println("allCountries = " + allCountries);
        assertEquals(5,allCountries.size());

    }
}
