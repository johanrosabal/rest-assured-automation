package com.blacksystem.automation.module.meetme.collections;

import com.blacksystem.automation.application.common.BaseTest;
import com.blacksystem.automation.module.meetme.dtos.AgentDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class RestAssuredAgents {
    public static Logger logger = LogManager.getLogger();

    /***
     * Post New Agent Request with baseUrl, agentDto, statusCode
     */
    public static Response postAgent(AgentDto agentDto, int statusCode){

        String request = agentDto.toJson();
        BaseTest.getRequestBody(request);

        String endPoint = RestAssured.baseURI+"/api/agents";
        BaseTest.getEndPoint(endPoint);
        BaseTest.pause();
        logger.info("****************************** Post Agent ["+ agentDto.getId() +"] ****************************** ");
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Post Agent Status Code Incorrect, ");
        return response;
    }

    /***
     * Post New Client Request with baseUrl, agentDto, statusCode, UUID active, Optional Id
     */
    public static Response postAgent (int statusCode, boolean UUID, int... id){

        AgentDto agentDto = new AgentDto().init();
        if(!UUID){
            agentDto.setId(String.valueOf(id[0]));
        }
        String request = agentDto.toJson();
        BaseTest.getRequestBody(request);

        String endPoint = RestAssured.baseURI+"/api/agents";
        BaseTest.getEndPoint(endPoint);
        BaseTest.pause();
        logger.info("****************************** Post Agent ["+ agentDto.getId() +"] ****************************** ");
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Post Agent Status Code Incorrect, ");
        return response;
    }

    /***
     * Post New Agent Request with baseUrl, NumberOfAgents, Status Code, UUID Active
     */
    public static List<Response> postAgents(int numberOfClients,int statusCode, boolean UUID){
        List<Response> responses = new ArrayList<>();
        for(int i=1; i<=numberOfClients;i++){

            Response response = postAgent(statusCode,UUID,i);
            responses.add(response);
        }

        return responses;
    }


    public static Response getAgentById(String id, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/agents/"+id;
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Get Agent By Id Status Code Incorrect, ");
        return response;
    }

    public static Response getAllAgents(int statusCode){
        String endPoint = RestAssured.baseURI+"/api/agents/";
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Get All Agents Status Code Incorrect, ");
        return response;
    }

    public static Response editAgentsById(String id, AgentDto agentDto, int statusCode){
        String request = agentDto.toJson();
        BaseTest.getRequestBody(request);

        String endPoint = RestAssured.baseURI+"/api/agents/"+id;
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Edit Agent By Id Status Code Incorrect, ");
        return response;
    }

    public static Response deleteAgentById(String id, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/agents/"+id;
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Delete Agent By Id Status Code Incorrect, ");
        return response;
    }

    public static Response updateCoinsAgentById(String id,int coins, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/agents/coins/"+id+"/"+coins;
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Update Coins Agent By Id Status Code Incorrect, ");
        return response;
    }

    public static Response inactiveAgent(String id, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/agents/active/"+id+"/inactive";
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Inactive Agent By Id Status Code Incorrect, ");
        return response;
    }

    public static Response activeAgent(String id, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/agents/active/"+id+"/activate";
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Active Agent By Id Status Code Incorrect, ");
        return response;
    }

    public static Response activeAgentEmail(String id, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/agents/email-confirmation/"+id+"/inactive";
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Inactive Agent By Id Status Code Incorrect, ");
        return response;
    }

    public static Response inactiveAgentEmail(String id, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/agents/email-confirmation/"+id+"/activate";
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Active Agent By Id Status Code Incorrect, ");
        return response;
    }

    public static Response updateAgentPhoneToken(String id, String token, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/agents/token/"+id+"/"+token;
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

    public static Response inactiveStatusAgent(String id, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/agents/status/"+id+"/inactive";
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Inactive Agent By Id Status Code Incorrect, ");
        return response;
    }

    public static Response activeStatusAgent(String id, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/agents/status/"+id+"/activate";
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Active Agent By Id Status Code Incorrect, ");
        return response;
    }

    public static Response updatesNotesAgent(String id,String notes, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/agents/notes/"+id;
        BaseTest.getEndPoint(endPoint);

        String request = "{\"notes\" : \""+notes+"\"}";
        BaseTest.getRequestBody(request);
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Active Agent By Id Status Code Incorrect, ");
        return response;
    }

    public static Response updatesTotalCallsAgent(String id,String calls, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/agents/total-calls/"+id+"/"+calls;
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Total Calls Agent By Id Status Code Incorrect, ");
        return response;
    }

    public static Response updatesRateAgent(String id,String rate, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/agents/rates/"+id+"/"+rate;
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Rate Agent By Id Status Code Incorrect, ");
        return response;
    }

    public static Response updatesSwipperPhotoAgent(String id,String swipper, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/agents/swipper/"+id;
        BaseTest.getEndPoint(endPoint);

        String request = "{\"swipperPhoto\" : \""+swipper+"\"}";
        BaseTest.getRequestBody(request);
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Swipper Photo Agent By Id Status Code Incorrect, ");
        return response;
    }

    public static Response updatesHeroPhotoAgent(String id,String hero, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/agents/hero/"+id;
        BaseTest.getEndPoint(endPoint);

        String request = "{\"heroPhoto\" : \""+hero+"\"}";
        BaseTest.getRequestBody(request);
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Swipper Photo Agent By Id Status Code Incorrect, ");
        return response;
    }

    public static Response updatesBankingInformationAgent(String id,String bankName,String bankAccount, int statusCode){
        String endPoint = RestAssured.baseURI+"/api/agents/banking/"+id;
        BaseTest.getEndPoint(endPoint);

        String request = "{\"bankName\" : \""+bankName+"\",\"bankAccount\" : \"" + bankAccount+"\"}";
        BaseTest.getRequestBody(request);
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

        Assert.assertEquals(response.getStatusCode(),statusCode,"[ERROR]: Banking Information Agent By Id Status Code Incorrect, ");
        return response;
    }

}
