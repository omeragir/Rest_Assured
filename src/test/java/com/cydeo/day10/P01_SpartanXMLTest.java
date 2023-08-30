package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class P01_SpartanXMLTest extends SpartanAuthTestBase {
    /**
     * Given accept type is application/xml
     * and basic auth is admin admin
     * When send the request /api/spartans
     * Then status code is 200
     * And content type is application/xml
     *   print first spartan name
     *   .....
     *   ...
     */

    @Test
   public void test1() {
        given().accept(ContentType.XML)
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans").prettyPeek()
                .then()
                .statusCode(200)
                .contentType("application/xml")
                .body("List.item[0].name",is("Meade"))
                .body("List.item[1].name",is("Nels"));

    }
@DisplayName("GET api/spartans with using XMLPAth")
    @Test
    public void test2() {
     Response response = given().accept(ContentType.XML)
            .auth().basic("admin", "admin")
            .when()
            .get("/api/spartans");

     XmlPath xmlPath = response.xmlPath();

     String firstSpartanNAme = xmlPath.getString("List.item[0].name");
    System.out.println("firstSpartanNAme = " + firstSpartanNAme);

    //can you get the name from db and compare with this variable

    //get me last spartan name
    System.out.println("xmlPath.getString(\"List.item[-1].name\") = " + xmlPath.getString("List.item[-1].name"));

    //get all spartan name
    List<String>allNames =xmlPath.getList("List.item[0].name");
    System.out.println("allNames = " + allNames);

}
}
