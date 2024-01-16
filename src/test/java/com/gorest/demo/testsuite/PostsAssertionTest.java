package com.gorest.demo.testsuite;

import com.gorest.demo.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class PostsAssertionTest extends TestBase {
    private static RequestSpecBuilder builder;

    private static RequestSpecification requestSpecification;
    static ValidatableResponse response;

    public PostsAssertionTest() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";
        response = given()
                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
        builder = new RequestSpecBuilder();
        builder.addHeader("Content-Type", "application/json");
        builder.addQueryParam("$per_page", 25);
        requestSpecification = builder.build();
    }

    @Test
    public void test001() {
        given().log().all()
                .header("Content-Type", "application/json")
                .queryParam("$per_page", 25);
    }

    @Test
    public void test002() {
        response.body("find{it.id ==93997}.title", equalTo("Demitto conqueror atavus argumentum corrupti cohaero libero."));
    }

    @Test
    public void test003() {
        response.body("user_id", hasItem(5914249));
    }

    @Test
    public void test004() {
        response.body("user_id", hasItems(5914249, 5914249, 5914243));
    }

    @Test

    public void test005() {
        response.body("find{it.user_id == 5914254}.body", equalTo("Depulso auris vereor. Acceptus suffragium repudiandae. Cotidie cubicularis deprecator. Virtus validus aliquid. Adduco somnus quibusdam. Despecto nihil vinum. Claudeo nam ullus. Sursum tutamen rerum. Cenaculum tabula adultus. Charisma thema super. Vobis cavus clibanus. Quo quod avaritia. Condico apparatus nulla. Textilis depopulo acidus."));

    }
}
