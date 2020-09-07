package com.blacksystem.automation.module.meetme.tests;

import com.blacksystem.automation.application.common.BaseTest;
import com.blacksystem.automation.module.meetme.collections.RestAssuredAgents;
import com.blacksystem.automation.module.meetme.dtos.*;
import com.blacksystem.automation.module.meetme.quicktype.Agent;
import com.blacksystem.automation.module.meetme.quicktype.Client;
import com.blacksystem.automation.module.meetme.quicktype.Converter;
import com.blacksystem.automation.module.meetme.quicktype.ResponseMessages;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MeetMeAgentsTest extends BaseTest {

    @Test(description = "Add 1s new agent")
    public void test01_Post_AddNewAgent(){
        List<Response> responses = RestAssuredAgents.post(1,200,true);

        for(Response response : responses){
            if(response.getStatusCode() != 200){
                assertGroup.fail( "[ERROR]: Status Code Fail, "+response.getBody().asString());
            }else{
                ResponseDto retrieveResponse = new ResponseDto(response);
                assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED","[ERROR]: Status Incorrect, ");
                assertGroup.assertEquals(retrieveResponse.getMessage(),"Agents has been saved successfully.","[ERROR]: Message missing, ");
                assertGroup.assertAll();
            }
        }
        assertGroup.assertAll();
    }

    @Test(description = "Get Customer By Id")
    public void test02_Get_AgentById(){
        AgentDto agentsDto = new AgentDto().init();

        Response responsePost = RestAssuredAgents.post(agentsDto,200);
        Assert.assertEquals(responsePost.getStatusCode(),200);

        Response responseGet  = RestAssuredAgents.getById(agentsDto.getId(),200);
        Assert.assertEquals(responseGet.getStatusCode(),200);

        AgentDto agentResponseDto = new AgentDto().fromResponse(responseGet);
        agentResponseDto.setId(agentsDto.getId());

        Assert.assertEquals(agentResponseDto,agentsDto);
    }

    @Test(description = "Get All Agents")
    public void test03_Get_AllAgents() throws IOException {
        Response responseGet = RestAssuredAgents.getAll(200);
        Assert.assertEquals(responseGet.getStatusCode(),200);
        ResponseMessages response = Converter.fromJsonString(responseGet);
        List<Agent> agents = Arrays.asList(response.getAgents());
        if(agents.size()==0){
            Assert.fail("Should be greater than Zero.");
        }
    }

    @Test(description = "Edit Agents By Id")
    public void test04_Put_EditAgentsById(){
        AgentDto agentsDto = new AgentDto().init();

        Response postAgentResponse = RestAssuredAgents.post(agentsDto,200);
        Assert.assertEquals(postAgentResponse.getStatusCode(),200);

        Response getAgentResponse  = RestAssuredAgents.getById(agentsDto.getId(),200);

        AgentDto agentRequestDto = new AgentDto().fromResponse(getAgentResponse);
        agentRequestDto.setFirstName(agentsDto.getFirstName()+"Edited");

        Response responsePut = RestAssuredAgents.editById(agentsDto.getId(),agentRequestDto,200);
        Assert.assertEquals(responsePut.getStatusCode(),200);
    }

    @Test(description = "Delete Agent By Id")
    public void test05_Del_AgentById(){
        AgentDto agentDto = new AgentDto().init();

        Response postAgentResponse = RestAssuredAgents.post(agentDto,200);
        Assert.assertEquals(postAgentResponse.getStatusCode(),200);

        Response deleteAgentResponse = RestAssuredAgents.deleteById(agentDto.getId(),200);
        Assert.assertEquals(deleteAgentResponse.getStatusCode(),200);

        Response getAgentResponse  = RestAssuredAgents.getById(agentDto.getId(),200);

        ResponseDto retrieveResponse = new ResponseDto(getAgentResponse);
        assertGroup.assertEquals(retrieveResponse.getStatus(),"FAILED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Agent By Id")
    public void test06_Put_InactiveAgentBy(){
        AgentDto agentDto = new AgentDto().init();
        Response postAgentResponse = RestAssuredAgents.post(agentDto,200);
        Assert.assertEquals(postAgentResponse.getStatusCode(),200);
        pause();
        Response response  = RestAssuredAgents.inactive(agentDto.getId(),200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),agentDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Agent is inactivate successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Agent By Id")
    public void test07_Put_ActiveAgentById(){
        AgentDto agentDto = new AgentDto().init();
        Response postAgentResponse = RestAssuredAgents.post(agentDto,200);
        Assert.assertEquals(postAgentResponse.getStatusCode(),200);
        pause();
        Response response  = RestAssuredAgents.active(agentDto.getId(),200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),agentDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Agent is active successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Agent Email By Id")
    public void test08_Put_InactiveAgentEmailById(){
        AgentDto agentDto = new AgentDto().init();
        Response postAgentResponse = RestAssuredAgents.post(agentDto,200);
        Assert.assertEquals(postAgentResponse.getStatusCode(),200);
        pause();
        Response response  = RestAssuredAgents.inactiveEmail(agentDto.getId(),200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),agentDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Agent is Email confirmation has been reset.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Agent email By Id")
    public void test09_Put_ActiveAgentEmailBy(){
        AgentDto agentDto = new AgentDto().init();
        Response postAgentResponse = RestAssuredAgents.post(agentDto,200);
        Assert.assertEquals(postAgentResponse.getStatusCode(),200);
        pause();
        Response response  = RestAssuredAgents.activeEmail(agentDto.getId(),200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),agentDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Agent is Email has confirmed.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Agent Phone Token")
    public void test10_Put_AgentPhoneToken(){
        AgentDto agentDto = new AgentDto().init();
        Response postAgentResponse = RestAssuredAgents.post(agentDto,200);
        Assert.assertEquals(postAgentResponse.getStatusCode(),200);
        pause();
        Response response  = RestAssuredAgents.updatePhoneToken(agentDto.getId(),"9999999-9999999-9999999-9999-0-88",200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),agentDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Phone Token has been updated successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Agent Notes By Id")
    public void test11_Put_AgentNotesById(){
        String notes = "Some Description by Automation Test.";

        AgentDto agentDto = new AgentDto().init();
        Response postAgentResponse = RestAssuredAgents.post(agentDto,200);

        Assert.assertEquals(postAgentResponse.getStatusCode(),200);
        pause();
        Response response  = RestAssuredAgents.updatesNotes(agentDto.getId(),notes,200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),agentDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Agents Notes has been updated successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Swipper Photo By Agent Id")
    public void test12_Put_Updates_SwipperPhoto_AgentById(){
        String swipper = "http://google.com";
        AgentDto agentDto = new AgentDto().init();
        Response postAgentResponse = RestAssuredAgents.post(agentDto,200);
        Assert.assertEquals(postAgentResponse.getStatusCode(),200);
        pause();

        Response putCoinsResponse  = RestAssuredAgents.updatesSwipperPhotoAgent(agentDto.getId(),swipper,200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getID(),agentDto.getId());
        assertGroup.assertEquals(response.getStatus(),"COMPLETED");
        assertGroup.assertEquals(response.getMessage(),"Agents Swipper Photo has been updated successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Hero Photo By Agent Id")
    public void test13_Put_Updates_HeroPhoto_AgentById(){
        String hero = "http://hotmail.com";
        AgentDto agentDto = new AgentDto().init();
        Response postAgentResponse = RestAssuredAgents.post(agentDto,200);
        Assert.assertEquals(postAgentResponse.getStatusCode(),200);
        pause();

        Response putCoinsResponse  = RestAssuredAgents.updatesHeroPhotoAgent(agentDto.getId(),hero,200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getID(),agentDto.getId());
        assertGroup.assertEquals(response.getStatus(),"COMPLETED");
        assertGroup.assertEquals(response.getMessage(),"Agents Hero Photo has been updated successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Banking Information By Agent Id")
    public void test14_Put_Updates_BankingInformation_AgentById(){
        String bankName = "http://hotmail.com";
        String bankAccount = "555555555555555";
        AgentDto agentDto = new AgentDto().init();
        Response postAgentResponse = RestAssuredAgents.post(agentDto,200);
        Assert.assertEquals(postAgentResponse.getStatusCode(),200);
        pause();

        Response putCoinsResponse  = RestAssuredAgents.updatesBankingInformationAgent(agentDto.getId(),bankName,bankAccount,200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getID(),agentDto.getId());
        assertGroup.assertEquals(response.getStatus(),"COMPLETED");
        assertGroup.assertEquals(response.getMessage(),"Agents Banking Information has been updated successfully.");
        assertGroup.assertAll();
    }
}
