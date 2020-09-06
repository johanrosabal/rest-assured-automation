package com.blacksystem.automation.module.meetme.tests;

import com.blacksystem.automation.application.common.BaseTest;
import com.blacksystem.automation.module.meetme.collections.RestAssuredAgents;
import com.blacksystem.automation.module.meetme.collections.RestAssuredCalls;
import com.blacksystem.automation.module.meetme.collections.RestAssuredClients;
import com.blacksystem.automation.module.meetme.dtos.*;
import com.blacksystem.automation.module.meetme.utils.RatesCalculation;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MeetMeCallsTest extends BaseTest {

    int callTimeInSeconds = 60;

    String callId;
    ClientDto clientDto;
    AgentDto agentDto;
    StartCallDto startCallDto;
    RatesCalculation ratesCalculation;


    @Test(description = "Add New Agent", priority = 1)
    public void test01_Add_New_Agent(){
        clientDto = new ClientDto().init();
        Response clientResponse = RestAssuredClients.post(clientDto,200);
        Assert.assertEquals(clientResponse.getStatusCode(),200,"Client Status Code Fail, ");
    }

    @Test(description = "Add New Client",priority = 2)
    public void test02_Add_New_Client(){
        agentDto = new AgentDto().init();
        Response agentResponse = RestAssuredAgents.post(agentDto,200);
        Assert.assertEquals(agentResponse.getStatusCode(),200,"Agent Status Code Fail, ");
    }

    @Test(description = "Change Agent Status On Line",priority = 3)
    public void test03_Change_Agent_Status_Online(){
        Response agentOnlineResponse = RestAssuredAgents.online(agentDto.getId(),true,200);
        Assert.assertEquals(agentOnlineResponse.getStatusCode(),200,"Agent Online Status Code Fail, ");

        Response responseGet  = RestAssuredAgents.getById(agentDto.getId(),200);
        Assert.assertEquals(responseGet.getStatusCode(),200);

        AgentDto agentResponseDto = new AgentDto().fromResponse(responseGet);
        Assert.assertTrue(agentResponseDto.isOnLine(),"Agent Online Value Incorrect");
    }

    @Test(description = "Start a Call",priority = 4)
    public void test04_Post_StartCall(){

        startCallDto = new StartCallDto();
        startCallDto.setAgentId(agentDto.getId());
        startCallDto.setClientId(clientDto.getId());
        startCallDto.setType("PRIVATE");

        Response startCallResponse = RestAssuredCalls.startCall(startCallDto,200);
        Assert.assertEquals(startCallResponse.getStatusCode(),200,"Start Call Status Code Fail, ");

        ResponseDto response = new ResponseDto(startCallResponse);
        callId = response.getID();

        assertGroup.assertEquals(response.getMessage(),"Calls has been saved successfully.");
        assertGroup.assertNotNull(response.getID(),"Start Call Id Should not be null, ");
        assertGroup.assertEquals(response.getStatus(),"COMPLETED","Start Call Status Code Incorrect, ");
        assertGroup.assertAll();
    }

    @Test(description = "Get Call by Id",priority = 5)
    public void test05_Get_StartCallById(){

        Response responseCallById = RestAssuredCalls.getById(callId,200);
        Assert.assertEquals(responseCallById.getStatusCode(),200,"Call By Id Status Code Fail, ");

        StartCallDto startCallDtoResponse = new StartCallDto().fromResponse(responseCallById);

        assertGroup.assertEquals(startCallDtoResponse.getAgentId(),startCallDto.getAgentId());
        assertGroup.assertEquals(startCallDtoResponse.getClientId(),startCallDto.getClientId());
        assertGroup.assertEquals(startCallDtoResponse.getType(),startCallDto.getType());
        assertGroup.assertEquals(startCallDtoResponse.getStatus(),"IN_PROGRESS");
        assertGroup.assertAll();

    }

    @Test(description = "End Call by Id",priority = 6)
    public void test06_EndCallById(){

        Response responseEndCallById = RestAssuredCalls.endCallById(callId,callTimeInSeconds,200);
        Assert.assertEquals(responseEndCallById.getStatusCode(),200,"End Call By Id Status Code Fail, ");

        ResponseDto response = new ResponseDto(responseEndCallById);

        assertGroup.assertEquals(response.getMessage(),"Calls has been updated successfully.");
        assertGroup.assertEquals(response.getID(),callId,"Call Id not match, ");
        assertGroup.assertEquals(response.getStatus(),"COMPLETED","End Call Status Incorrect, ");
        assertGroup.assertAll();
    }

    @Test(description = "Verify Complete Status Ended call",priority = 7)
    public void test07_Call_Complete_Status(){
        pause();
        Response callCompleted = RestAssuredCalls.getById(callId,200);

        EndCallDto endCallResponse = new EndCallDto().fromResponse(callCompleted);

        ratesCalculation = new RatesCalculation(endCallResponse.getStartDate(),endCallResponse.getEndDate(),endCallResponse.getAgentRate());

        assertGroup.assertEquals(endCallResponse.getAgentId(),agentDto.getId(),"[ERROR]: CallID ["+callId+"] Incorrect Agent Id, ");
        assertGroup.assertEquals(endCallResponse.getClientId(),clientDto.getId(),"[ERROR]: CallID [\"+callId+\"] Incorrect Client Id, ");
        assertGroup.assertEquals(endCallResponse.getSessionId(),callId,"[ERROR]: CallID [\"+callId+\"] Incorrect Call Id, ");
        assertGroup.assertEquals(endCallResponse.getType(),"PRIVATE","[ERROR]: CallID [\"+callId+\"] Incorrect Type Call, ");
        assertGroup.assertNotNull(endCallResponse.getStartDate(),"[ERROR]: CallID [\"+callId+\"] Start date should not be null, ");
        assertGroup.assertNotNull(endCallResponse.getEndDate(),"[ERROR]: CallID [\"+callId+\"] End date should not be null, ");

        assertGroup.assertEquals(endCallResponse.getStatus(),"COMPLETED","[ERROR]: CallID [\"+callId+\"] Incorrect Status Call, ");
        assertGroup.assertEquals(endCallResponse.getDurationInSeconds(),ratesCalculation.getDurationInSeconds(),"[ERROR]: [\"+callId+\"] Incorrect Duration In Seconds, ");
        assertGroup.assertEquals(endCallResponse.getDurationInMilliseconds(),ratesCalculation.getDurationInMilliseconds(),"[ERROR]: [\"+callId+\"] Incorrect Duration In Milliseconds, ");
        assertGroup.assertEquals(endCallResponse.getDuration(),ratesCalculation.getDuration(),"[ERROR]: [\"+callId+\"] Incorrect Duration, ");

        assertGroup.assertEquals(endCallResponse.getRatePerMinute(),ratesCalculation.getRatePerMinute(),"[ERROR]: [\"+callId+\"] Incorrect Rates x Minute, ");
        assertGroup.assertEquals(endCallResponse.getRatePerSecond(),ratesCalculation.getRatePerSecond(),"[ERROR]: [\"+callId+\"] Incorrect Rates x Seconds, ");

        assertGroup.assertEquals(endCallResponse.getTotalInSeconds(),ratesCalculation.getDurationInSeconds(),"[ERROR]: [\"+callId+\"] Incorrect Duration In Seconds, ");
        assertGroup.assertEquals(endCallResponse.getTotalInMinutes(),ratesCalculation.getTotalInMinutes(),"[ERROR]: [\"+callId+\"] Incorrect Duration In Minutes, ");

        assertGroup.assertEquals(endCallResponse.getTotalCall(),ratesCalculation.getTotalCall(),"[ERROR]: [\"+callId+\"] Incorrect Total Call, ");
        assertGroup.assertEquals(endCallResponse.getTotalService(),ratesCalculation.getTotalService(),"[ERROR]: [\"+callId+\"] Incorrect Total Service, ");
        assertGroup.assertEquals(endCallResponse.getTotalAgent(),ratesCalculation.getTotalAgent(),"[ERROR]: [\"+callId+\"] Incorrect Total Agent, ");
        assertGroup.assertEquals(endCallResponse.getTotalCoins(),ratesCalculation.getTotalCoins(),"[ERROR]: [\"+callId+\"] Incorrect Total Coins, ");

        assertGroup.assertNotNull(endCallResponse.getDateModified());
        assertGroup.assertAll();
    }

    @Test(description = "Verify Agent Fields Updated on End Call",priority = 8)
    public void test08_Update_Agent_End_Call(){

        Response response = RestAssuredAgents.getById(agentDto.getId(),200);
        Assert.assertEquals(response.getStatusCode(),200);

        AgentDto agentResponseDto = new AgentDto().fromResponse(response);
        assertGroup.assertTrue(!agentResponseDto.isOnCall(),"[ERROR]: Agent Id["+agentDto.getId()+"] OnCall Status Incorrect.");
        assertGroup.assertEquals(agentResponseDto.getCoins(),ratesCalculation.getTotalCoins(),"[ERROR]: Agent Id["+agentDto.getId()+"] Total Coins Incorrect.");
        assertGroup.assertEquals(agentResponseDto.getTotalCalls(),1,"[ERROR]: Agent Id["+agentDto.getId()+"] Total Call Incorrect.");
        assertGroup.assertAll();

    }

}
