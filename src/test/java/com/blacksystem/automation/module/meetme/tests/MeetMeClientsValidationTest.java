package com.blacksystem.automation.module.meetme.tests;

import com.blacksystem.automation.application.common.BaseTest;
import com.blacksystem.automation.module.meetme.collections.RestAssuredClients;
import com.blacksystem.automation.module.meetme.dtos.ClientDto;
import com.blacksystem.automation.module.meetme.dtos.ClientErrorsDto;
import com.blacksystem.automation.module.meetme.dtos.ResponseDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MeetMeClientsValidationTest extends BaseTest {

    @Test(description = "Post Empty Client Verify Required Fields")
    public void test01_Post_Client_Validation(){
        ClientDto clientDto = new ClientDto();

        Response response  = RestAssuredClients.post(clientDto,200);

        ClientErrorsDto errors = new ClientErrorsDto(response);
        assertGroup.assertEquals(errors.getId(),"Id must be provided.");
        assertGroup.assertEquals(errors.getFirstName(),"First name must be provided.");
        assertGroup.assertEquals(errors.getLastName(),"Last name must be provided.");
        assertGroup.assertEquals(errors.getSecondLastName(),"Second last name must be provided.");
        assertGroup.assertEquals(errors.getBirthDate(),"Birth Date must be provided.");
        assertGroup.assertEquals(errors.getGender(),"Gender must be provided.");
        assertGroup.assertEquals(errors.getNickName(),"Nickname must be provided.");
        assertGroup.assertEquals(errors.getEmail(),"Email must be provided.");
        assertGroup.assertEquals(errors.getLanguage(),"Language must be provided.");
        assertGroup.assertEquals(errors.getCountry(), "Country Code must be provided.");
        assertGroup.assertEquals(errors.getPrivacyPolicy(), "Privacy Policy value must be provided.");
        assertGroup.assertAll();
    }

    @Test(description = "Get Client By Id when Client is not exist")
    public void test02_Get_ClientById_Validation(){
        ClientDto clientDto = new ClientDto().init();
        Response responseGet  = RestAssuredClients.getById(clientDto.getId(),200);
        Assert.assertEquals(responseGet.getStatusCode(),200);

        ResponseDto response = new ResponseDto(responseGet);
        assertGroup.assertEquals(response.getID(),clientDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Edit Client By Id when client not exist")
    public void test03_Put_EditClientById_Validation(){
        ClientDto clientDto = new ClientDto().init();
        Response responseGet  = RestAssuredClients.editById(clientDto.getId(),clientDto,200);
        Assert.assertEquals(responseGet.getStatusCode(),200);

        ResponseDto response = new ResponseDto(responseGet);
        assertGroup.assertEquals(response.getID(),clientDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Delete Client By Id when client not exist")
    public void test04_Del_ClientById_Validation(){
        ClientDto clientDto = new ClientDto().init();
        Response responseGet  = RestAssuredClients.deleteById(clientDto.getId(),200);
        Assert.assertEquals(responseGet.getStatusCode(),200);

        ResponseDto response = new ResponseDto(responseGet);
        assertGroup.assertEquals(response.getID(),clientDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Client Coins By Id when Client is not exist")
    public void test05_Put_Client_Coins_Validation(){
        ClientDto clientDto = new ClientDto().init();

        Response putCoinsResponse  = RestAssuredClients.updateCoinsById(clientDto.getId(),5,200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getID(),clientDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Client By Id when Client is not exist")
    public void test06_Put_InactiveClientBy_Validation(){
        ClientDto clientDto = new ClientDto().init();

        Response putCoinsResponse  = RestAssuredClients.inactiveClient(clientDto.getId(),200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getID(),clientDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Client By Id when Client is not exist")
    public void test07_Put_ActiveClientBy_Validation(){
        ClientDto clientDto = new ClientDto().init();

        Response putCoinsResponse  = RestAssuredClients.activeRecord(clientDto.getId(),200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getID(),clientDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Client Email By Id when Client is not exist")
    public void test08_Put_InactiveClientEmailBy_Validation(){
        ClientDto clientDto = new ClientDto().init();

        Response putCoinsResponse  = RestAssuredClients.inactiveEmail(clientDto.getId(),200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getID(),clientDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Client email By Id when Client is not exist")
    public void test09_Put_ActiveClientEmailBy_Validation(){
        ClientDto clientDto = new ClientDto().init();

        Response putCoinsResponse  = RestAssuredClients.activeEmail(clientDto.getId(),200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getID(),clientDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Client Phone Token when Client is not exist")
    public void test10_Put_ClientPhoneToken_Validation(){
        ClientDto clientDto = new ClientDto().init();

        Response putCoinsResponse  = RestAssuredClients.updatePhoneToken(clientDto.getId(),"9999999-9999999-9999999-9999-0-88",200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getID(),clientDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }
}
