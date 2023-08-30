package com.cydeo.task;

import com.cydeo.pojo.Region;
import com.cydeo.pojo.RegionPojo;
import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P06_PostPutDelete extends HrTestBase {

    @DisplayName("Post Regions")
    @Test
    public void test1() {

        Map<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("region_id", 155);
        requestMap.put("region_name", "TestRegion");
        System.out.println("requestMap = " + requestMap);

        Response response =
                given().log().ifValidationFails()
                        .accept(ContentType.JSON)
                        .and()
                        .contentType(ContentType.JSON)
                        .body(requestMap)
                        .when()
                        .post("/regions/")
                        .then()
                        .statusCode(201)
                        .contentType("application/json")
                        .extract().response();
        JsonPath jsonPath = response.jsonPath();

        int regionId = jsonPath.getInt("region_id");
        String regionName = jsonPath.getString("region_name");
        assertEquals(155, regionId);
        assertEquals("TestRegion", regionName);

    }

    @Test
    public void test2() {
        Response response = given().log().ifValidationFails()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .get("/regions/155")
                .then()
                .statusCode(200)
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int regionId = jsonPath.getInt("region_id");
        String regionName = jsonPath.getString("region_name");
        System.out.println("regionName = " + regionName);
        System.out.println("regionId = " + regionId);
        assertEquals(155, regionId);
        assertEquals("TestRegion", regionName);

    }

    @DisplayName("With Pojo")
    @Test
    public void test3() {

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(new RegionPojo(255, "Test"))
                .post("/regions/").prettyPeek();


    }
}
