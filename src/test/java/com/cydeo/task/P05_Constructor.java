package com.cydeo.task;

import com.cydeo.pojo.ConstructorPOJO;
import com.cydeo.utilities.FormulaTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
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

    @DisplayName("JsonPath")
    @Test
    public void test5() {

        JsonPath jsonPath =
                given().accept(ContentType.JSON)
                        .and()
                        .when().get("/constructorStandings/1/constructors.json")
                        .then().log().all()
                        .statusCode(200)
                        .contentType("application/json; charset=utf-8")
                        .extract().jsonPath();
        //- Use JSONPATH
//    int total = jsonpath.getInt(“pathOfField”)
        /*
        And total is 17
- And limit is 30
- And each constructor has constructorId
- And constructor has name
- And size of constructor is 17
- And constructor IDs has “benetton”, “mercedes”,”team_lotus”
         */
        int total = jsonPath.getInt("MRData.total");
        assertEquals(17, total);
        assertEquals(30, jsonPath.getInt("MRData.limit"));
        List<Map> constructors = jsonPath.getList("MRData.ConstructorTable.Constructors");
        for (Map constructor : constructors) {
            assertTrue(constructor.containsKey("name"));
        }

        assertEquals(17, constructors.size());

        List<String> ids = Arrays.asList("benetton", "mercedes", "team_lotus");
        List<String> actualIds = jsonPath.getList("MRData.ConstructorTable.Constructors.constructorId");

        assertTrue(actualIds.containsAll(ids));
    }

    @DisplayName("HamCrest")
    @Test
    public void test6() {

        JsonPath jsonPath =
                given().accept(ContentType.JSON)
                        .and()
                        .when().get("/constructorStandings/1/constructors.json")
                        .then().log().all()
                        .statusCode(200)
                        .contentType("application/json; charset=utf-8")
                        .body("MRData.total",equalTo("17"))
                        .body("MRData.limit",equalTo("30"))
                        .body(("MRData.ConstructorTable.Constructors"),everyItem(hasKey("constructorId")))
                        .body("MRData.ConstructorTable.Constructors",everyItem(hasKey("name")))
                        .body("MRData.ConstructorTable.Constructors",hasSize(17))
                        .body("MRData.ConstructorTable.Constructors.constructorId",hasItems("benetton", "mercedes", "team_lotus"))
                        .extract().jsonPath();

        List<String> names = jsonPath.getList("MRData.ConstructorTable.Constructors.name");
        System.out.println("names = " + names);
    }

    @DisplayName("Java Structure")
    @Test
    public void test7(){

        JsonPath jsonPath =
                given().accept(ContentType.JSON)
                        .and()
                        .when().get("/constructorStandings/1/constructors.json")
                        .then().log().all()
                        .statusCode(200)
                        .contentType("application/json; charset=utf-8")
                        .body("MRData.total",equalTo("17"))
                        .body("MRData.limit",equalTo("30"))
                        .extract().jsonPath();

        List<Map<String,Object>> constructorMap=jsonPath.getList("MRData.ConstructorTable.Constructors");
        for (Map<String, Object> eachConstructor : constructorMap) {
            assertTrue(eachConstructor.containsKey("constructorId"));
            assertTrue(eachConstructor.containsKey("name"));
        }

        assertEquals(17, constructorMap.size());

        List<String> constructorIds=jsonPath.getList("MRData.ConstructorTable.Constructors.constructorId");

        List<String> ids = Arrays.asList("benetton", "mercedes", "team_lotus");

        assertTrue(constructorIds.containsAll(ids));
    }

    @DisplayName("POJO Class")
    @Test
    public void test8(){

        JsonPath jsonPath =
                given().accept(ContentType.JSON)
                        .and()
                        .when().get("/constructorStandings/1/constructors.json")
                        .then().log().all()
                        .statusCode(200)
                        .contentType("application/json; charset=utf-8")
                        .body("MRData.total",equalTo("17"))
                        .body("MRData.limit",equalTo("30"))
                        .extract().jsonPath();

        List<ConstructorPOJO> constructor=jsonPath.getList("MRData.ConstructorTable.Constructors",ConstructorPOJO.class);
        System.out.println("constructor = " + constructor);

        assertEquals(17, constructor.size());

        for (ConstructorPOJO constructorPOJO : constructor) {
            assertTrue(!constructorPOJO.getConstructorId().isEmpty());
            assertTrue(!constructorPOJO.getName().isEmpty());
        }

        List<String> ids = Arrays.asList("benetton", "mercedes", "team_lotus");
        List<String> actualIds = new ArrayList<>();
        for (ConstructorPOJO constructorPOJO : constructor) {
            actualIds.add(constructorPOJO.getConstructorId());
        }

        assertTrue(actualIds.containsAll(ids));

    }
}


