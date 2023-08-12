package com.cydeo.day03;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


import java.nio.file.Path;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_HrWithParameters extends HrTestBase {
        /*
            TASK 1 :
         - Given accept type is Json
        - And parameters: q="{"region_id":2}"
        - When users sends request to /countries
        - Then status code is 200
        - And Content - Type is Json
        - And Country_name is United States of America


         */

    @Test
    public void countriesParam() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParams("q", "{\"region_id\":2}")
                .when().get("/countries");

        response.prettyPrint();


        //- Then status code is 200
        assertEquals(200,response.statusCode());
        //- And Content - Type is Json
        assertEquals("application/json",response.contentType());

        //- And Country_name is United States of America
        assertTrue(response.body().asString().contains("United States of America"));
    }


}
