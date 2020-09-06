package com.blacksystem.automation.module.meetme.collections;

import com.blacksystem.automation.application.common.BaseTest;
import com.blacksystem.automation.module.meetme.dtos.StartCallDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class RestAssuredCalls {

    public static Logger logger = LogManager.getLogger();

    public static Response post(StartCallDto startCallDto, int statusCode){
        String request = startCallDto.toJson();
        logger.info("****************************** Post Calls  ****************************** ");
        return post(request,statusCode);
    }

    /***
     * Post New Agent Request with baseUrl, agentDto, statusCode
     */
    public static Response post(String request, int statusCode){

        BaseTest.getRequestBody(request);
        String endPoint = RestAssured.baseURI+"/api/calls/start";
        BaseTest.getEndPoint(endPoint);
        BaseTest.pause();

        Response response = given()
                .headers("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when()
                .post(endPoint)
                .then()
                .extract()
                .response();

        BaseTest.getResponse(response);
        BaseTest.getStatusCode(response);
        BaseTest.getResponseTime(response);

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Post Calls Status Code Incorrect, ");
        return response;
    }
}
