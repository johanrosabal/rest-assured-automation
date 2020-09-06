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

    public static Response startCall(StartCallDto startCallDto, int statusCode){
        String request = startCallDto.toJson();
        logger.info("****************************** Post Calls  ****************************** ");
        return startCall(request,statusCode);
    }

    /***
     * Post Start Call
     */
    public static Response startCall(String request, int statusCode){

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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Post Start Calls Status Code Incorrect, ");
        return response;
    }

    /***
     * Post End Call
     */
    public static Response endCallById(String id,int callTimeInSeconds, int statusCode){
        BaseTest.pause(callTimeInSeconds);
        BaseTest.pause();
        String endPoint = RestAssured.baseURI+"/api/calls/end/"+id;
        BaseTest.getEndPoint(endPoint);

        Response response = given()

                .when()
                .put(endPoint)
                .then()
                .extract()
                .response();

        BaseTest.getResponse(response);
        BaseTest.getStatusCode(response);
        BaseTest.getResponseTime(response);

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Post End Calls Status Code Incorrect, ");
        return response;
    }

    /***
     * Get Call Information By Id
     */
    public static Response getById(String id, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/calls/"+id;
        BaseTest.getEndPoint(endPoint);

        Response response = given()

                .when()
                .get(endPoint)
                .then()
                .extract()
                .response();

        BaseTest.getResponse(response);
        BaseTest.getStatusCode(response);
        BaseTest.getResponseTime(response);

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Get Call By Id Status Code Incorrect, ");
        return response;
    }


}
