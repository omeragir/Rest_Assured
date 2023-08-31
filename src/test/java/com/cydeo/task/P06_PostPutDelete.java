package com.cydeo.task;

import com.cydeo.pojo.Region;
import com.cydeo.pojo.RegionPojo;
import com.cydeo.utilities.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P06_PostPutDelete extends HrTestBase {

    private int global_region_id;

    @Test
    void create_region() {
        /*
        Map<String,Object> requestedBody =new LinkedHashMap<>();
        requestedBody.put("region_id",378);
        requestedBody.put("region_name","Test Region");

         */
        /*
        {
"region_id":100,
"region_name":"Test Region"
}
         */
        Random random = new Random();
        int random_region_id = random.nextInt(1000);


        int expected_region_id = random_region_id;
        String expected_region_name="Fatih Region";

        global_region_id = expected_region_id;

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON) //"application/json"
                .contentType(ContentType.JSON)
                .body(new RegionPojo(expected_region_id,expected_region_name))//With Pojo Class
                //   .body("        {\n" + "\"region_id\":100,\n" +   "\"region_name\":\"Test Region\" \n" + "}\n")
                .post("/regions/").prettyPeek();

        int actualRegionId = response.jsonPath().getInt("region_id");
        String actualRegionName = response.jsonPath().getString("region_name");

       assertEquals(expected_region_id,actualRegionId);

        assertEquals(expected_region_name,actualRegionName);


    }

    /*
    —> GET
    Given accept is json
    When I send GET request to "/regions/100" Then status code is 200
    And content type is json
    And region_id is 100
    And region_name is Test Region
     */
    @Test
    void get_region() {

      given().accept(ContentType.JSON)
                .when().get("/regions/"+global_region_id);

    }

}
