package com.cydeo.day04;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_HrWithJsonPath extends HrTestBase {

    @DisplayName("GET all countries")
    @Test
    public void test1() {

        Response response = get("/countries");
        //response.prettyPrint();

        //verify status code
        assertEquals(200, response.statusCode());

        //create Json Path
        JsonPath jsonPath = response.jsonPath();
        //get me 3rd country name
        System.out.println("jsonPath.getString(\"items[2].country_name\") = " + jsonPath.getString("items[2].country_name"));

        //get me 3rd and 4th country name same time
        System.out.println("jsonPath.getString(\"items[2,3].country_name\") = " + jsonPath.getString("items[2,3].country_name"));

        //get me all country name where region_id is 2
        List<String> list = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("list = " + list);

    }

     /*
    Given accept type is application/json
    And query param limit is 200
    When user send request /employees
    Then user should see ............

     */

    @DisplayName("GET all /employees?limit=200 with JSONPATH")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .queryParams("limit", 200)
                .when().get("/employees");
        //response.prettyPrint();


        response.getStatusCode();
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json", response.contentType());
        //create json object
        JsonPath jsonPath = response.jsonPath();

        //get all emails from response
        List<String> allEmails = jsonPath.getList("items.email");
        System.out.println("allEmails = " + allEmails);
        System.out.println("allEmails.size() = " + allEmails.size());

        //get all who is working as IT_PROG
        List<String> emailsIT = jsonPath.getList("items.findAll {it.job_id=='IT_PROG'}.email");
        System.out.println("emailsIT = " + emailsIT);

        //get me all employees first name whose salary is more than 10000
        List<Object> allEmpSalaryMoreThan10K = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("allEmpSalaryMoreThan10K = " + allEmpSalaryMoreThan10K);
        //get me all information from response who has max salary
        System.out.println("jsonPath.getString(\"items.max {it.salary}\") = " + jsonPath.getString("items.max {it.salary}"));


        //get me first name from response who has max salary
        System.out.println("jsonPath.getString(\"items.max {it.salary}\") = " + jsonPath.getString("items.max {it.salary}.first_name"));
        //get me first name from response who has min salary
        System.out.println("jsonPath.getString(\"items.min {it.salary}\") = " + jsonPath.getString("items.min {it.salary}.first_name"));

    }

    /*

TASK
Given
        accept type is application/json
When
        user sends get request to /locaitons
Then
        response status code must be 200
        content type equals to application/json
        get the second city with JsonPath
        get the last city with JsonPath
        get all country ids
        get all city where their country id is UK

 */
    @DisplayName("GET information from locations")
    @Test
    public void test3() {

        Response response = given().accept(ContentType.JSON)
                .and()
                .when().get("/locations");
        //response.prettyPrint();
        //response status code must be 200
        assertEquals(200,response.statusCode());
        // content type equals to application/json
        assertEquals("application/json",response.contentType());
        //create json path object
         JsonPath jsonPath = response.jsonPath();
         //get the second city with JsonPath
        System.out.println("jsonPath.getString(\"items[1].city\") = " + jsonPath.getString("items[1].city"));
        // get the last city with JsonPath
        System.out.println("jsonPath.getString(\"items[-1].city\") = " + jsonPath.getString("items[-1].city"));
        // get all country ids
        System.out.println("jsonPath.getList(\"items.country_id\") = " + jsonPath.getList("items.country_id"));
        //get all city where their country id is UK
        System.out.println("jsonPath.getList(\"items.findAll {it.country_id=='UK'}\") = " + jsonPath.getList("items.findAll {it.country_id=='UK'}.city"));

    }


}
