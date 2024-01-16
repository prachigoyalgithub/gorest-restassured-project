package com.gorest.demo.testsuite;

import com.gorest.demo.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class UserExtractionTest extends TestBase {
    static ValidatableResponse response;

    public UserExtractionTest() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/users")
                .then().statusCode(200);
    }

    @Test
    public void test001() {
        List<Integer> userIDs = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all Ids are : " + userIDs);
        System.out.println("------------------End of Test----------------------------");
    }

    @Test
    public void test002() {
        List<String> userNames = response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the names are : " + userNames);
        System.out.println("------------------End of Test----------------------------");
    }

    @Test
    public void test003() {
        String userName = response.extract().path("[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th object is : " + userName);
        System.out.println("------------------End of Test----------------------------");
    }

    @Test
    public void test004() {
        List<String> names = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all object whose status = inactive : " + names);
        System.out.println("------------------End of Test----------------------------");
    }

    @Test
    public void test005() {
        List<String> gender = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The gender of all the object whose status = active : " + gender);
        System.out.println("------------------End of Test----------------------------");
    }

    @Test
    public void test006() {
        List<String> name = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of the object whose gender = female : " + name);
        System.out.println("------------------End of Test----------------------------");
    }

    @Test
    public void test007() {
        List<String> emails = response.extract().path("findAll{it.status == 'inactive'}.emails");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The gender of all the objects whose status = active : " + emails);
        System.out.println("------------------End of Test----------------------------");
    }

    @Test
    public void test008() {
        List<Object> ids = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ids of the object where gender = male : " + ids);
        System.out.println("------------------End of Test----------------------------");
    }

    @Test
    public void test009() {
        List<String> status = response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all the status are : " + status);
        System.out.println("------------------End of Test----------------------------");
    }

    @Test
    public void test010() {
        List<String> email = response.extract().path("findAll{it.name == 'Satyen Adiga III'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The email of the object where name = Ganak Jain : " + email);
        System.out.println("------------------End of Test----------------------------");
    }

    @Test
    public void test011() {
        List<String> gender = response.extract().path("findAll{it.id == 2322249}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The gender of id = 2322249 : " + gender);
        System.out.println("------------------End of Test----------------------------");

    }
}
