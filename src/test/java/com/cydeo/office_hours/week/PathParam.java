package com.cydeo.office_hours.week;

import com.cydeo.utilities.FruitTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class PathParam extends FruitTestBase {

    /**
     * 1- Given accept type is Json
     * 2- Path Parameters value is
     * <p>
     * id —> 4
     * 3- When user sends GET request to /products/{id}*4- Verify followings
     * Status code should be 200
     * Content Type is application/json
     * Print response
     * id is 4
     * Name is "Grapes"
     * Vendor name is "Max Obsthof GmbH"
     **/

    @Test
    public void getSingleProduct() {
        JsonPath jsonPath =
                given()
                        .log().uri()
                        .accept(ContentType.JSON)
                        .pathParam("id", 4)
                .when()
                        .get("/products/{id}").prettyPeek()
                .then()
                        .statusCode(200)
                        .contentType("application/json")
                        .extract().jsonPath();

        // - Status code should be 200
        //Assertions.assertEquals(200,response.statusCode());
        //Assertions.assertEquals(200,response.getStatusCode());
        //Assertions.assertEquals(HttpStatus.SC_OK,response.getStatusCode());
        //Assertions.assertEquals(HttpStatus.SC_OK,response.statusCode());
        //
        //
        //- Content Type is application/json
        //Assertions.assertEquals("application/json",response.contentType());
        //Assertions.assertEquals("application/json",response.getContentType());
        //
        //Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());
        //Assertions.assertEquals(ContentType.JSON.toString(),response.getContentType());

        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String vendorName = jsonPath.getString("vendors[0].name");
        assertThat(id, is(4));
        assertThat(name, is(equalTo("Coconut")));
        assertThat(vendorName, is(equalTo("True Fruits Inc.")));

    }

}
