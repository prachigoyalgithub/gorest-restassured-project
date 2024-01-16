package com.gorest.demo.testsuite;

import com.gorest.demo.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;


public class UserAssertionTest extends TestBase {
        static ValidatableResponse response;
        private static RequestSpecBuilder builder;

        private static RequestSpecification requestSpecification;

        @BeforeClass
        public void inIt() {
            RestAssured.baseURI = "https://gorest.co.in";
            RestAssured.basePath = "/public/v2";
            response = given()
                    .when()
                    .get("/users?page=1&per_page=20")
                    .then().statusCode(200);
            builder = new RequestSpecBuilder();
            builder.addHeader("Content-Type", "application/json");
            builder.addQueryParam("$per_page", 20);
            requestSpecification = builder.build();
        }

        //1. Verify the if the total record is 20
        @Test
        public void test001(){
            given().log().all()
                    .header("Content-Type", "application/json")
                    .queryParam("$per_page", 20);

        }
        @Test
        public void test002() {
            response.body("find{it.id ==5914152}.name", equalTo("Anshula Joshi"));
        }

        @Test
        public void test003() {
            response.body("[2].name", equalTo("Avantika Kaur"));
        }

        @Test
        public void test004() {
            response.body("name", hasItems("Anshula Joshi", "Avantika Kaur", "Somu Pillai"));
        }

        @Test
        public void test005() {
            response.body("find{it.id = 5914152}.email", equalTo("anshula_joshi@dare.test"));
        }

        @Test
        public void test006() {
            response.body("find{it.name=='Yoginder Dhawan Esq.'}.status", equalTo("active"));
        }

        @Test
        public void test007() {
            response.body("find{it.name='Dinesh Mehrotra'}.gender", equalTo("male"));
        }


    }
