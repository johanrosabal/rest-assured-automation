package com.blacksystem.automation.module.meetme.tests;

import com.blacksystem.automation.application.common.BaseTest;
import com.blacksystem.automation.module.meetme.collections.RestAssuredAgents;
import com.blacksystem.automation.module.meetme.dtos.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class MeetMeAgentsTest extends BaseTest {

    @Test(description = "Add 1s new agent")
    public void test01_Post_AddNewAgent(){
        List<Response> responses = RestAssuredAgents.postAgents(1,200,true);

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

        Response responsePost = RestAssuredAgents.postAgent(agentsDto,200);
        Assert.assertEquals(responsePost.getStatusCode(),200);

        Response responseGet  = RestAssuredAgents.getAgentById(agentsDto.getId(),200);
        Assert.assertEquals(responseGet.getStatusCode(),200);

        AgentDto agentResponseDto = new AgentDto().fromResponse(responseGet);
        agentResponseDto.setId(agentsDto.getId());

        Assert.assertEquals(agentResponseDto,agentsDto);
    }

    @Test(description = "Get All Agents")
    public void test03_Get_AllAgents(){
        Response responseGet = RestAssuredAgents.getAllAgents(200);
        Assert.assertEquals(responseGet.getStatusCode(),200);

        AgentsDto agents = new AgentsDto(responseGet);

        if(agents.getAgents().size() == 0){
            Assert.fail("[ERROR]: Get All Agents should not be 0.");
        }
    }

    @Test(description = "Edit Agents By Id")
    public void test04_Put_EditAgentsById(){
        AgentDto agentsDto = new AgentDto().init();

        Response postAgentResponse = RestAssuredAgents.postAgent(agentsDto,200);
        Assert.assertEquals(postAgentResponse.getStatusCode(),200);

        Response getAgentResponse  = RestAssuredAgents.getAgentById(agentsDto.getId(),200);

        AgentDto agentRequestDto = new AgentDto().fromResponse(getAgentResponse);
        agentRequestDto.setFirstName(agentsDto.getFirstName()+"Edited");

        Response responsePut = RestAssuredAgents.editAgentsById(agentsDto.getId(),agentRequestDto,200);
        Assert.assertEquals(responsePut.getStatusCode(),200);
    }

    @Test(description = "Delete Agent By Id")
    public void test05_Del_AgentById(){
        AgentDto agentDto = new AgentDto().init();

        Response postAgentResponse = RestAssuredAgents.postAgent(agentDto,200);
        Assert.assertEquals(postAgentResponse.getStatusCode(),200);

        Response deleteAgentResponse = RestAssuredAgents.deleteAgentById(agentDto.getId(),200);
        Assert.assertEquals(deleteAgentResponse.getStatusCode(),200);

        Response getAgentResponse  = RestAssuredAgents.getAgentById(agentDto.getId(),200);

        ResponseDto retrieveResponse = new ResponseDto(getAgentResponse);
        assertGroup.assertEquals(retrieveResponse.getID(),agentDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"FAILED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Agent Coins By Id")
    public void test06_Put_Agent_Coins(){
        AgentDto agentDto = new AgentDto().init();
        Response postAgentResponse = RestAssuredAgents.postAgent(agentDto,200);
        Assert.assertEquals(postAgentResponse.getStatusCode(),200);

        Response response  = RestAssuredAgents.updateCoinsAgentById(agentDto.getId(),5,200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),agentDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Coins has been updated successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Agent By Id")
    public void test07_Put_InactiveAgentBy(){
        AgentDto agentDto = new AgentDto().init();
        Response postAgentResponse = RestAssuredAgents.postAgent(agentDto,200);
        Assert.assertEquals(postAgentResponse.getStatusCode(),200);
        pause();
        Response response  = RestAssuredAgents.inactiveAgent(agentDto.getId(),200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),agentDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Active has been updated successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Agent By Id")
    public void test08_Put_ActiveAgentBy(){
        AgentDto agentDto = new AgentDto().init();
        Response postAgentResponse = RestAssuredAgents.postAgent(agentDto,200);
        Assert.assertEquals(postAgentResponse.getStatusCode(),200);
        pause();
        Response response  = RestAssuredAgents.activeAgent(agentDto.getId(),200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),agentDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Active has been updated successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Agent Email By Id")
    public void test09_Put_InactiveAgentEmailBy(){
        AgentDto agentDto = new AgentDto().init();
        Response postAgentResponse = RestAssuredAgents.postAgent(agentDto,200);
        Assert.assertEquals(postAgentResponse.getStatusCode(),200);
        pause();
        Response response  = RestAssuredAgents.inactiveAgentEmail(agentDto.getId(),200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),agentDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Email Verified has been updated successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Agent email By Id")
    public void test10_Put_ActiveAgentEmailBy(){
        AgentDto agentDto = new AgentDto().init();
        Response postAgentResponse = RestAssuredAgents.postAgent(agentDto,200);
        Assert.assertEquals(postAgentResponse.getStatusCode(),200);
        pause();
        Response response  = RestAssuredAgents.activeAgentEmail(agentDto.getId(),200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),agentDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Email Verified has been updated successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Agent Phone Token")
    public void test11_Put_AgentPhoneToken(){
        AgentDto agentDto = new AgentDto().init();
        Response postAgentResponse = RestAssuredAgents.postAgent(agentDto,200);
        Assert.assertEquals(postAgentResponse.getStatusCode(),200);
        pause();
        Response response  = RestAssuredAgents.updateAgentPhoneToken(agentDto.getId(),"9999999-9999999-9999999-9999-0-88",200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),agentDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Phone Token has been updated successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Post Empty Agent Verify Required Fields")
    public void test12_Post_Agent_Validation(){
        AgentDto agentDto = new AgentDto();

        Response response  = RestAssuredAgents.postAgent(agentDto,200);

        AgentErrorsDto errors = new AgentErrorsDto(response);
        assertGroup.assertEquals(errors.getId(),"field must be provided.");
        assertGroup.assertEquals(errors.getFirstName(),"First name must be provided.");
        assertGroup.assertEquals(errors.getLastName(),"Last name must be provided.");
        assertGroup.assertEquals(errors.getSecondLastName(),"Second last name must be provided.");
        assertGroup.assertEquals(errors.getSsn(),"Social Security Number must be provided.");

        assertGroup.assertEquals(errors.getBirthDate(),"Cannot read property 'split' of undefined");
        assertGroup.assertEquals(errors.getGender(),"Gender must be provided.");
        assertGroup.assertEquals(errors.getNickName(),"Nickname must be provided.");
        assertGroup.assertEquals(errors.getEmail(),"Is not a valid email.");
        assertGroup.assertEquals(errors.getPhoneNumber(),"Phone Number must be provided.");

        assertGroup.assertEquals(errors.getLanguage(),"Language must be provided.");
        assertGroup.assertEquals(errors.getCountry(), "Country Code must be provided.");

        assertGroup.assertEquals(errors.getPhoneToken(), "Phone Token must be provided.");
        assertGroup.assertEquals(errors.getBankAccount(), "Bank Account must be provided.");
        assertGroup.assertEquals(errors.getBankName(), "Bank Name must be provided.");
        assertGroup.assertEquals(errors.getDescription(), "Description must be provided.");
        assertGroup.assertEquals(errors.getSwipperPhoto(), "Swipper Photo must be provided.");
        assertGroup.assertEquals(errors.getHeroPhoto(), "Hero Photo must be provided.");

        assertGroup.assertAll();
    }

    @Test(description = "Get Agent By Id when Agent is not exist")
    public void test13_Get_AgentById_Validation(){
        AgentDto agentDto = new AgentDto().init();
        Response responseGet  = RestAssuredAgents.getAgentById(agentDto.getId(),200);
        Assert.assertEquals(responseGet.getStatusCode(),200);

        ResponseDto response = new ResponseDto(responseGet);
        assertGroup.assertEquals(response.getID(),agentDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Edit Agent By Id when agent not exist")
    public void test14_Put_EditAgentById_Validation(){
        AgentDto agentDto = new AgentDto().init();
        Response responseGet  = RestAssuredAgents.editAgentsById(agentDto.getId(),agentDto,200);
        Assert.assertEquals(responseGet.getStatusCode(),200);

        ResponseDto response = new ResponseDto(responseGet);
        assertGroup.assertEquals(response.getID(),agentDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Delete Agent By Id when agent not exist")
    public void test15_Del_AgentById_Validation(){
        AgentDto agentDto = new AgentDto().init();
        Response responseGet  = RestAssuredAgents.deleteAgentById(agentDto.getId(),200);
        Assert.assertEquals(responseGet.getStatusCode(),200);

        ResponseDto response = new ResponseDto(responseGet);
        assertGroup.assertEquals(response.getID(),agentDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Agent Coins By Id when Agent is not exist")
    public void test16_Put_Agent_Coins_Validation(){
        AgentDto agentDto = new AgentDto().init();

        Response putCoinsResponse  = RestAssuredAgents.updateCoinsAgentById(agentDto.getId(),5,200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getID(),agentDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Agent By Id when Agent is not exist")
    public void test17_Put_InactiveAgentBy_Validation(){
        AgentDto agentDto = new AgentDto().init();

        Response putCoinsResponse  = RestAssuredAgents.inactiveAgent(agentDto.getId(),200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getID(),agentDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Agent By Id when Agent is not exist")
    public void test18_Put_ActiveAgentBy_Validation(){
        AgentDto agentDto = new AgentDto().init();

        Response putCoinsResponse  = RestAssuredAgents.activeAgent(agentDto.getId(),200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getID(),agentDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Agent Email By Id when Agent is not exist")
    public void test19_Put_InactiveAgentEmailBy_Validation(){
        AgentDto agentDto = new AgentDto().init();

        Response putCoinsResponse  = RestAssuredAgents.inactiveAgentEmail(agentDto.getId(),200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getID(),agentDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Agent email By Id when Agent is not exist")
    public void test20_Put_ActiveAgentEmailBy_Validation(){
        AgentDto agentDto = new AgentDto().init();

        Response putCoinsResponse  = RestAssuredAgents.activeAgentEmail(agentDto.getId(),200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getID(),agentDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Agent Phone Token when Agent is not exist")
    public void test21_Put_AgentPhoneToken_Validation(){
        AgentDto agentDto = new AgentDto().init();

        Response putCoinsResponse  = RestAssuredAgents.updateAgentPhoneToken(agentDto.getId(),"9999999-9999999-9999999-9999-0-88",200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getID(),agentDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }


}
