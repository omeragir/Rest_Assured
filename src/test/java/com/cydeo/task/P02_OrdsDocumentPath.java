package com.cydeo.task;

import com.cydeo.utilities.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_OrdsDocumentPath extends HrTestBase {


    //TASK 1 :
    //- Given accept type is Json
    //- Path param value- US
    //- When users sends request to /countries
    //- Then status code is 200
    //- And Content - Type is Json
    //- And country_id is US
    //- And Country_name is United States of America
    //And Region_id is 2

    @Test
    public void pathTask() {

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("country_name", "US")
                .when().get("/countries/{country_name}");

        //- Then status code is 200
        assertEquals(200, response.statusCode());

        //- And Content - Type is Json
        assertEquals("application/json", response.contentType());

        //- And country_id is US
        assertEquals("US", response.path("country_id"));

        //- And Country_name is United States of America
        assertEquals("United States of America", response.path("country_name"));
        //And Region_id is 2
        int regionId = response.path("region_id");
        assertEquals(2, regionId);
    }

    @Test
    public void queryPathParam() {

        //TASK 2 :
        //- Given accept type is Json
        //- Query param value - q={"department_id":80}
        //- When users sends request to /employees

        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"department_id\":80}")
                .when().get("/employees");

        //response.prettyPrint();

        //- Then status code is 200
        assertEquals(200, response.getStatusCode());
        //- And Content - Type is Json
        assertEquals("application/json", response.contentType());
        //- And all job_ids start with 'SA'
        List<String> jobIds = response.path("items.job_id");
        for (String each : jobIds) {
            assertTrue(each.startsWith("SA"));

        }
        //- And all department_ids are 80
        List<Integer> departmentsId = response.path("items.department_id");
        for (Integer each : departmentsId) {
            assertEquals(80, each);
        }
        //- Count is 25
        assertEquals(25, (Integer) response.path("count"));


    }

    @Test
    public void task3() {
        //TASK 3 :
        //- Given accept type is Json
        //- Query param value q={“region_id":3}
        //- When users sends request to /countries
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParams("q", "{\"region_id\":3}")
                .when().get("/countries");

        // response.prettyPrint();

        //- Then status code is 200
        assertEquals(200, response.statusCode());
        //- And all regions_id is 3
        List<Integer> allRegionId = response.path("items.region_id");
        for (Integer eachId : allRegionId) {
            assertEquals(3, eachId);
        }
        //- And count is 6
        int count = response.path("count");
        assertEquals(6, count);
        //- And hasMore is false
        assertEquals("false", response.path("hasMore").toString());
        //- And Country_name are;
        //Australia,China,India,Japan,Malaysia,Singapore
        List<String> allCountryName = response.path("items.country_name");
        List<String> expectedCountryName = new ArrayList<>
                (Arrays.asList("Australia","China","India","Japan","Malaysia","Singapore"));
        assertEquals(expectedCountryName, allCountryName);

    }


}
