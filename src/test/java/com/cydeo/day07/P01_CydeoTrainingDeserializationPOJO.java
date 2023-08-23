package com.cydeo.day07;

import com.cydeo.pojo.Student;
import com.cydeo.pojo.Students;
import com.cydeo.utilities.CydeoTestBase;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class P01_CydeoTrainingDeserializationPOJO extends CydeoTestBase {


    @DisplayName("GET /student/2")
    @Test
    public void test1() {
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 2)
                .when().get("/student/{id}");
        //verify status code
        assertEquals(200, response.statusCode());


        JsonPath jsonPath = response.jsonPath();

        //Deserialize to Student class
        Student student = jsonPath.getObject("students[0]", Student.class);

//        firstName Mark
//        batch 13
//        major math
//        emailAddress mark@email.com
//        companyName Cydeo
//        street 777 5th Ave
//        zipCode 33222
        System.out.println("student.getFirstName() = " + student.getFirstName());
        System.out.println("student.getContact().getEmailAddress() = " + student.getContact().getEmailAddress());

        assertEquals("Mark",student.getFirstName());

        assertEquals(13,student.getBatch());

        assertEquals("math",student.getMajor());

        assertEquals("mark@email.com",student.getContact().getEmailAddress());

        assertEquals("Cydeo",student.getCompany().getCompanyName());

        assertEquals("777 5th Ave",student.getCompany().getAddress().getStreet());

        assertEquals(33222,student.getCompany().getAddress().getZipCode());


    }

    @DisplayName("GET /student/2")
    @Test
    public void test2() {
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 2)
                .when().get("/student/{id}");
        //verify status code
        assertEquals(200, response.statusCode());


        JsonPath jsonPath = response.jsonPath();

        //Deserialize to Students class
        Students students = jsonPath.getObject("", Students.class);
        //we deserialize everything to Students class which is holding list of Student
        System.out.println("students = " + students);
        Student student = students.getStudents().get(0);

        //if there is no path, we can use response.as method for deserilaztion
        //Students studentsWithAs = response.as(Students.class);

//        firstName Mark
//        batch 13
//        major math
//        emailAddress mark@email.com
//        companyName Cydeo
//        street 777 5th Ave
//        zipCode 33222
        System.out.println("student.getFirstName() = " + student.getFirstName());
        System.out.println("student.getContact().getEmailAddress() = " + student.getContact().getEmailAddress());

        assertEquals("Mark",student.getFirstName());

        assertEquals(13,student.getBatch());

        assertEquals("math",student.getMajor());

        assertEquals("mark@email.com",student.getContact().getEmailAddress());

        assertEquals("Cydeo",student.getCompany().getCompanyName());

        assertEquals("777 5th Ave",student.getCompany().getAddress().getStreet());

        assertEquals(33222,student.getCompany().getAddress().getZipCode());


    }

    @DisplayName("GET /student/2")
    @Test
    public void test3() {
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 2)
                .when().get("/student/{id}");
        //verify status code
        assertEquals(200, response.statusCode());
        JsonPath jsonPath = response.jsonPath();

        com.cydeo.pojo.ready.Student student = jsonPath.getObject("students[0]", com.cydeo.pojo.ready.Student.class);

        System.out.println("student.getJoinDate() = " + student.getJoinDate());
        System.out.println("student.getCompany().getStartDate() = " + student.getCompany().getStartDate());
        System.out.println("student.getCompany().getAddress().getState() = " + student.getCompany().getAddress().getState());


    }
}