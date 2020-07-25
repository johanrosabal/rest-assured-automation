package com.blacksystem.automation.module.demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Test_PATCH {

    @Test
    public void test_1_patch(){

        JSONObject request = new JSONObject();

        request.put("name","Johan");
        request.put("job","QA Automation");

        System.out.println(request.toJSONString());

        //Run Test Post
        given()
                .headers("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
        .when()
                .patch("https://reqres.in/api/users/2")
        .then()
                .statusCode(200)
                .log().all(); // 201 Successful Creation

        Response response = RestAssured.get("https://reqres.in/api/users/2");
        System.out.println(response.body().asString());
    }
}
