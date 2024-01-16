package com.gorest.demo.testsuite;

import com.gorest.demo.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostExtractionTest {
    public class PostsExtractionTest extends TestBase {
        static ValidatableResponse response;

        public PostsExtractionTest() {
            RestAssured.baseURI = "https://gorest.co.in";
            RestAssured.basePath = "/public/v2";
            response = given()
                    .queryParam("page", 1)
                    .queryParam("per_page", 25)
                    .when()
                    .get("/posts")
                    .then().statusCode(200);
        }

        @Test
        public void test001() {
            List<String> titles = response.extract().path("title");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The all title are : " + titles);
            System.out.println("------------------End of Test----------------------------");
        }
    }
}
