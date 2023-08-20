package com.cydeo.task;

import com.cydeo.utilities.FormulaTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class P05_Constructor extends FormulaTestBase {

    //- Given accept type is json
    //- When user send request /constructorStandings/1/constructors.json
    //- Then verify status code is 200
    //- And content type is application/json; charset=utf-8
    //- And total is 17
    //- And limit is 30
    //- And each constructor has constructorId
    //- And constructor has name
    //- And size of constructor is 17
    //- And constructor IDs has “benetton”, “mercedes”,”team_lotus”

    @DisplayName("TASK 1 & SOLUTION WITH JSONPATH")
    @Test
    public void test2_1() {

         JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .when()
                .get("/constructorStandings/1/constructors.json").prettyPeek()
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                 .body("MRData.ConstructorTable.Constructors",hasSize(17))
                 .body("MRData.ConstructorTable.Constructors.name",everyItem(notNullValue()))
                 .body("MRData.ConstructorTable.Constructors.constructorId",everyItem(notNullValue()))
                 .body("MRData.ConstructorTable.Constructors.constructorId", containsInRelativeOrder("benetton", "mercedes", "team_lotus"))
                .extract().jsonPath();

        int limit = jsonPath.getInt("MRData.limit");
        assertThat(limit,is(30));
        int total = jsonPath.getInt("MRData.total");
        assertThat(total,is(17));

    }
    @DisplayName("TASK 1 & SOLUTION WITH HAMCREST MATCHERS")
    @Test
    public void test1_2() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/constructorStandings/1/constructors.json")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("MRData.total", is(equalTo("17")))
                .body("MRData.limit", is(equalTo("30")))
                .body("MRData.ConstructorTable.Constructors",hasSize(17))
                .body("MRData.ConstructorTable.Constructors.name",everyItem(notNullValue()))
                .body("MRData.ConstructorTable.Constructors.constructorId",everyItem(notNullValue()))
                .body("MRData.ConstructorTable.Constructors.constructorId", containsInRelativeOrder("benetton", "mercedes", "team_lotus"));

    }
    @DisplayName("TASK 1 & SOLUTION WITH JAVA STRUCTURE")
    @Test
    public void test1_3() {
        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .when()
                .get("/constructorStandings/1/constructors.json")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("MRData.ConstructorTable.Constructors",hasSize(17))
                .body("MRData.ConstructorTable.Constructors.name",everyItem(notNullValue()))
                .body("MRData.ConstructorTable.Constructors.constructorId",everyItem(notNullValue()))
                .body("MRData.ConstructorTable.Constructors.constructorId", containsInRelativeOrder("benetton", "mercedes", "team_lotus"))
                .extract().jsonPath();

        List<Map<String,Object>> constructorMap=jsonPath.getList("MRData.ConstructorTable.Constructors");

        for (Map<String, Object> each : constructorMap) {
            System.out.println("each = " + each);
        }

    }
}


