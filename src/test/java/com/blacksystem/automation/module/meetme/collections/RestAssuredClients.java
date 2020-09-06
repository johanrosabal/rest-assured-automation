package com.blacksystem.automation.module.meetme.collections;

import com.blacksystem.automation.application.common.BaseTest;
import com.blacksystem.automation.module.meetme.dtos.ClientDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class RestAssuredClients {
    public static Logger logger = LogManager.getLogger();

    /***
     * Post New Client Request with baseUrl, clientDTO, statusCode
     */
    public static Response post(ClientDto clientDto, int statusCode){

        String request = clientDto.toJson();
        BaseTest.getRequestBody(request);

        String endPoint = RestAssured.baseURI+"/api/clients";
        BaseTest.getEndPoint(endPoint);
        BaseTest.pause();
        logger.info("****************************** Post Client ["+ clientDto.getId() +"] ****************************** ");
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Post Client Status Code Incorrect, ");
        return response;
    }

    /***
     * Post New Client Request with baseUrl, clientDTO, statusCode, UUID active, Optional Id
     */
    public static Response post(int statusCode, boolean UUID, int... id){

        ClientDto clientDto = new ClientDto().init();
        if(!UUID){
            clientDto.setId(String.valueOf(id[0]));
        }
        String request = clientDto.toJson();
        BaseTest.getRequestBody(request);

        String endPoint = RestAssured.baseURI+"/api/clients";
        BaseTest.getEndPoint(endPoint);
        BaseTest.pause();
        logger.info("****************************** Post Client ["+ clientDto.getId() +"] ****************************** ");
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Post Client Status Code Incorrect, ");
        return response;
    }

    /***
     * Post New Client Request with baseUrl, NumberOfClients, Status Code, UUID Active
     */
    public static List<Response> post(int numberOfClients, int statusCode, boolean UUID){
        List<Response> responses = new ArrayList<>();
        for(int i=1; i<=numberOfClients;i++){

            Response response = post(statusCode,UUID,i);
            responses.add(response);
        }

        return responses;
    }


    public static Response getById(String id, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/clients/"+id;
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Get Client By Id Status Code Incorrect, ");
        return response;
    }

    public static Response getAll(int statusCode){
        String endPoint = RestAssured.baseURI+"/api/clients/";
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Get All Clients Status Code Incorrect, ");
        return response;
    }

    public static Response editById(String id, ClientDto clientDto, int statusCode){
        String request = clientDto.toJson();
        BaseTest.getRequestBody(request);

        String endPoint = RestAssured.baseURI+"/api/clients/"+id;
        BaseTest.getEndPoint(endPoint);

        Response response = given()

         .when()
                .headers("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .put(endPoint)
         .then()
                .extract()
                .response();

        BaseTest.getResponse(response);
        BaseTest.getStatusCode(response);
        BaseTest.getResponseTime(response);

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Edit Client By Id Status Code Incorrect, ");
        return response;
    }

    public static Response deleteById(String id, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/clients/"+id;
        BaseTest.getEndPoint(endPoint);

        Response response = given()

         .when()
                .delete(endPoint)
         .then()
                .extract()
                .response();

        BaseTest.getResponse(response);
        BaseTest.getStatusCode(response);
        BaseTest.getResponseTime(response);

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Delete Client By Id Status Code Incorrect, ");
        return response;
    }

    public static Response updateCoinsById(String id, int coins, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/clients/coins/"+id+"/"+coins;
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Update Coins Client By Id Status Code Incorrect, ");
        return response;
    }

    public static Response inactiveClient(String id, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/clients/active/"+id+"/inactive";
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Inactive Client By Id Status Code Incorrect, ");
        return response;
    }

    public static Response activeRecord(String id, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/clients/active/"+id+"/activate";
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Active Client By Id Status Code Incorrect, ");
        return response;
    }

    public static Response activeEmail(String id, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/clients/email-confirmation/"+id+"/inactive";
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Inactive Client By Id Status Code Incorrect, ");
        return response;
    }

    public static Response inactiveEmail(String id, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/clients/email-confirmation/"+id+"/activate";
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Active Client By Id Status Code Incorrect, ");
        return response;
    }

    public static Response updatePhoneToken(String id, String token, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/clients/token/"+id+"/"+token;
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Phone Token By Id Status Code Incorrect, ");
        return response;
    }

}
