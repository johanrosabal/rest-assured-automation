package com.blacksystem.automation.module.meetme.tests;

import com.blacksystem.automation.application.common.BaseTest;
import com.blacksystem.automation.module.meetme.collections.RestAssuredAgents;
import com.blacksystem.automation.module.meetme.dtos.AgentDto;
import com.blacksystem.automation.module.meetme.dtos.AgentErrorsDto;
import com.blacksystem.automation.module.meetme.dtos.ResponseDto;
import com.blacksystem.automation.module.meetme.quicktype.Converter;
import com.blacksystem.automation.module.meetme.quicktype.ResponseMessages;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class MeetMeAgentsValidationTest extends BaseTest {

    @Test(description = "Post Empty Agent Verify Required Fields")
    public void test01_Post_Agent_Validation() throws IOException {

        Response response  = RestAssuredAgents.post("{}",200);

        ResponseMessages data = Converter.fromJsonString(response);
        System.out.println(data);

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

        assertGroup.assertAll();
    }

    @Test(description = "Get Agent By Id when Agent is not exist")
    public void test02_Get_AgentById_Validation(){
        AgentDto agentDto = new AgentDto();
        agentDto.setId("1234abc");

        Response responseGet  = RestAssuredAgents.getById(agentDto.getId(),200);
        Assert.assertEquals(responseGet.getStatusCode(),200);

        ResponseDto response = new ResponseDto(responseGet);
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Edit Agent By Id when agent not exist")
    public void test03_Put_EditAgentById_Validation(){
        AgentDto agentDto = new AgentDto();
        agentDto.setId("1234abc");

        Response responseGet  = RestAssuredAgents.editById(agentDto.getId(),agentDto,200);
        Assert.assertEquals(responseGet.getStatusCode(),200);
        pause();
        ResponseDto response = new ResponseDto(responseGet);
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Delete Agent By Id when agent not exist")
    public void test04_Del_AgentById_Validation(){
        AgentDto agentDto = new AgentDto();
        agentDto.setId("1234abc");

        Response responseGet  = RestAssuredAgents.deleteById(agentDto.getId(),200);
        Assert.assertEquals(responseGet.getStatusCode(),200);

        ResponseDto response = new ResponseDto(responseGet);
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }


    @Test(description = "Inactive Agent By Id when Agent is not exist")
    public void test05_Put_InactiveAgentBy_Validation(){
        AgentDto agentDto = new AgentDto();
        agentDto.setId("1234abc");

        Response putCoinsResponse  = RestAssuredAgents.inactive(agentDto.getId(),200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Agent By Id when Agent is not exist")
    public void test06_Put_ActiveAgentBy_Validation(){
        AgentDto agentDto = new AgentDto().init();
        agentDto.setId("1234abc");

        Response putCoinsResponse  = RestAssuredAgents.active(agentDto.getId(),200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Agent Email By Id when Agent is not exist")
    public void test07_Put_InactiveAgentEmailBy_Validation(){
        AgentDto agentDto = new AgentDto().init();
        agentDto.setId("1234abc");

        Response putCoinsResponse  = RestAssuredAgents.inactiveEmail(agentDto.getId(),200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Agent email By Id when Agent is not exist")
    public void test08_Put_ActiveAgentEmailBy_Validation(){
        AgentDto agentDto = new AgentDto().init();
        agentDto.setId("1234abc");

        Response putCoinsResponse  = RestAssuredAgents.activeEmail(agentDto.getId(),200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Agent Phone Token when Agent is not exist")
    public void test09_Put_AgentPhoneToken_Validation(){
        AgentDto agentDto = new AgentDto().init();
        agentDto.setId("1234abc");

        Response putCoinsResponse  = RestAssuredAgents.updatePhoneToken(agentDto.getId(),"9999999-9999999-9999999-9999-0-88",200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Agent Notes when Agent not exist")
    public void test10_Put_AgentNotesById_Validation(){
        String notes = "Some Description by Automation Test.";

        AgentDto agentDto = new AgentDto();
        agentDto.setId("1234abc");

        Response responseNotes  = RestAssuredAgents.updatesNotes(agentDto.getId(),notes,200);

        ResponseDto response = new ResponseDto(responseNotes);
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Swipper Photo when Agent not exist")
    public void test11_Put_Updates_SwipperPhoto_AgentById_Validation(){
        String swipper = "http://google.com";

        AgentDto agentDto = new AgentDto();
        agentDto.setId("1234abc");

        Response responseSwipper  = RestAssuredAgents.updatesSwipperPhotoAgent(agentDto.getId(),swipper,200);

        ResponseDto response = new ResponseDto(responseSwipper);
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Hero Photo when Agent not exist")
    public void test12_Put_Updates_HeroPhoto_AgentById_Validation(){
        String hero = "http://hotmail.com";

        AgentDto agentDto = new AgentDto();
        agentDto.setId("1234abc");

        Response responseHero  = RestAssuredAgents.updatesHeroPhotoAgent(agentDto.getId(),hero,200);

        ResponseDto response = new ResponseDto(responseHero);
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Banking Information when Agent not exist")
    public void test13_Put_Updates_BankingInformation_AgentById_Validation(){
        String bankName = "http://hotmail.com";
        String bankAccount = "555555555555555";

        AgentDto agentDto = new AgentDto();

        Response responseBankingInfo  = RestAssuredAgents.updatesBankingInformationAgent(agentDto.getId(),bankName,bankAccount,200);

        ResponseDto response = new ResponseDto(responseBankingInfo);
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

}
