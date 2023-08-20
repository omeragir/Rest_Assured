package com.cydeo.task;

import com.cydeo.pojo.Formula;
import com.cydeo.utilities.FormulaTestBase;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class P04_Formula extends FormulaTestBase {
    //- Given accept type is json
    //- And path param driverId is alonso
    //- When user send request /drivers/{driverId}.json
    //- Then verify status code is 200
    //- And content type is application/json; charset=utf-8
    //- And total is 1
    //- And givenName is Fernando
    //- And familyName is Alonso
    //- And nationality is Spanish
    @DisplayName("TASK 1 & SOLUTION WITH JAVA STRUCTURE")
    @Test
    public void test1_3() {
        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .pathParam("driverId", "alonso")
                .when()
                .get("/drivers/{driverId}.json")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .extract().jsonPath();

        Map<String, Object> mrData = jsonPath.getMap("MRData");
        assertThat(mrData.get("total"), is("1"));

        Map<String, Object> drivers = jsonPath.getMap("MRData.DriverTable.Drivers[0]");

        assertThat(drivers.get("givenName"), is(equalTo("Fernando")));
        assertThat(drivers.get("familyName"), is(equalTo("Alonso")));
        assertThat(drivers.get("nationality"), is(equalTo("Spanish")));
    }

    @DisplayName("TASK 1 & SOLUTION WITH JSONPATH")
    @Test
    public void test2() {

        Response response =
                given().accept(ContentType.JSON)
                        .and()
                        .pathParam("driverId", "alonso")
                        .and()
                        .when()
                        .get("/drivers/{driverId}.json");
        response.prettyPrint();
        response.statusCode();
        assertThat(response.contentType(), is("application/json; charset=utf-8"));

        JsonPath jsonPath = response.jsonPath();
        Map<String, Object> jsonPathMap = jsonPath.getMap("MRData.DriverTable.Drivers[0]");

        assertThat(1, is(jsonPath.getInt("MRData.total")));
        assertThat("Fernando", is(equalTo(jsonPath.getString("MRData.DriverTable.Drivers[0].givenName"))));
        assertEquals("Alonso", jsonPath.getString("MRData.DriverTable.Drivers[0].familyName"));
        assertEquals("Spanish", jsonPath.getString("MRData.DriverTable.Drivers[0].nationality"));
    }

    @DisplayName("TASK 1 & SOLUTION WITH HAMCREST MATCHERS")
    @Test
    public void tes3() {

        given().accept(ContentType.JSON)
                .pathParam("driverId", "alonso")
                .when()
                .get("/drivers/{driverId}.json").prettyPeek()
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("MRData.DriverTable.Drivers[0].givenName", is(equalTo("Fernando")))
                .body("MRData.DriverTable.Drivers[0].familyName", is(equalTo("Alonso")))
                .body("MRData.total", is(equalTo("1")))
                .body("MRData.DriverTable.Drivers[0].nationality", is(equalTo("Spanish")));


    }

    @DisplayName("TASK 1 & SOLUTION WITH POJO CLASS")
    @Test
    public void test4() {
        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .pathParam("driverId", "alonso")
                .when()
                .get("/drivers/{driverId}.json")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .extract().jsonPath();

        Formula formula = jsonPath.getObject("MRData.DriverTable.Drivers[0]", Formula.class);
        formula.setTotal(jsonPath.get("MRData.total"));

        assertThat(formula.getTotal(), is("1"));
        assertThat(formula.getNationality(), is(equalTo("Spanish")));
        assertThat(formula.getGivenName(), is(equalTo("Fernando")));
        assertThat(formula.getFamilyName(), is(equalTo("Alonso")));

    }

}
