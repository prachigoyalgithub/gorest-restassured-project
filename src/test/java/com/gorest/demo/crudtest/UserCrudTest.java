package com.gorest.demo.crudtest;

import com.gorest.demo.model.UserPojo;
import com.gorest.demo.testbase.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class UserCrudTest extends TestBase {
        static ValidatableResponse response;
        static int id;
        private static Object TestUtils;
        static String name = TestUtils.getClass() + "Mona";

        @Test
        public void verifyUserCreatedSuccessfully() {
            UserPojo userPojo = new UserPojo();
            userPojo.setName("ABC");
            userPojo.setEmail( TestUtils.getClass()+"monal@gmail.com");
            userPojo.setGender("male");
            userPojo.setStatus("active");
            Response response = given()
                    .header("Authorization", "Bearer a4805c7c5cda603b60345e721f166223ec510ce5c21e457586a3e20739d66d85")
                    .header("Content-Type", "application/json")
                    .header("Connection", "keep-alive")
                    .when()
                    .body(userPojo)
                    .post("/users");
            response.prettyPrint();
            response.then().statusCode(201);
        }
        @Test
        public void verifyUserGetSuccessfully(){

            Response response = given()
                    .header("Authorization", "Bearer a4805c7c5cda603b60345e721f166223ec510ce5c21e457586a3e20739d66d85")
                    .header("Connection", "keep-alive")
                    .when()
                    .get("/users");
            response.prettyPrint();
            response.then().statusCode(200);

        }
        @Test
        public void verifyUserUpdateSuccessfully(){
            int storeIdToUpdate = id;
            UserPojo userPojo = new UserPojo();
            userPojo.setName(name + "UpdatedName");
            userPojo.setEmail( TestUtils.getClass()+"monal@gmail.com");
            userPojo.setGender("male");
            userPojo.setStatus("inactive");
            Response response = given()
                    .contentType(ContentType.JSON)
                    .pathParam("id", storeIdToUpdate)
                    .header("Authorization", "Bearer a4805c7c5cda603b60345e721f166223ec510ce5c21e457586a3e20739d66d85")
                    .header("Content-Type", "application/json")
                    .header("Connection", "keep-alive")
                    .when()
                    .body(userPojo)
                    .put("/users/{id}");
            response.prettyPrint();
            response.then().statusCode(200);
        }
        @Test
        public void VerifyUserDeleteSuccessfully(){
            Response response = given()
                    .pathParam("id", id)
                    .header("Authorization", "Bearer a4805c7c5cda603b60345e721f166223ec510ce5c21e457586a3e20739d66d85")
                    .header("Connection", "keep-alive")
                    .when()
                    .delete("/{id}");
            response.prettyPrint();
            response.then().statusCode(204);

            given()
                    .pathParam("id", id)
                    .when()
                    .get("/{id}")
                    .then()
                    .statusCode(404);
        }
    }

