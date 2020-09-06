package com.blacksystem.automation.module.meetme.tests;

import com.blacksystem.automation.application.common.BaseTest;
import com.blacksystem.automation.module.meetme.collections.RestAssuredClients;
import com.blacksystem.automation.module.meetme.dtos.ClientDto;
import com.blacksystem.automation.module.meetme.dtos.ResponseDto;
import com.blacksystem.automation.module.meetme.quicktype.Client;
import com.blacksystem.automation.module.meetme.quicktype.ResponseMessages;
import com.blacksystem.automation.module.meetme.quicktype.Converter;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MeetMeClientsTest extends BaseTest {

    @Test(description = "Add 1s new clients")
    public void test01_Post_AddNewClient(){
        List<Response> responses = RestAssuredClients.post(1,200,true);

        for(Response response : responses){
            if(response.getStatusCode() != 200){
                assertGroup.fail( "[ERROR]: Status Code Fail, "+response.getBody().asString());
            }else{
                ResponseDto retrieveResponse = new ResponseDto(response);
                assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED","[ERROR]: Status Incorrect, ");
                assertGroup.assertEquals(retrieveResponse.getMessage(),"Client has been saved successfully.","[ERROR]: Message missing, ");
                assertGroup.assertAll();
            }
        }
        assertGroup.assertAll();
    }

    @Test(description = "Get Customer By Id")
    public void test02_Get_ClientById(){
        ClientDto clientDto = new ClientDto().init();

        Response responsePost = RestAssuredClients.post(clientDto,200);
        Assert.assertEquals(responsePost.getStatusCode(),200);

        Response responseGet  = RestAssuredClients.getById(clientDto.getId(),200);
        Assert.assertEquals(responseGet.getStatusCode(),200);

        ClientDto clientResponseDto = new ClientDto().fromResponse(responseGet);
        clientResponseDto.setId(clientDto.getId());

        Assert.assertEquals(clientResponseDto,clientDto);
    }

    @Test(description = "Get All Customers")
    public void test03_Get_AllClients() throws IOException {
        Response responseGet = RestAssuredClients.getAll(200);
        Assert.assertEquals(responseGet.getStatusCode(),200);
        ResponseMessages response = Converter.fromJsonString(responseGet);
        List<Client> clients = Arrays.asList(response.getClients());
        if(clients.size()==0){
            Assert.fail("Should be greater than Zero.");
        }
    }

    @Test(description = "Edit Client By Id")
    public void test04_Put_EditClientById(){
        ClientDto clientDto = new ClientDto().init();

        Response postClientResponse = RestAssuredClients.post(clientDto,200);
        Assert.assertEquals(postClientResponse.getStatusCode(),200);

        Response getClientResponse  = RestAssuredClients.getById(clientDto.getId(),200);

        ClientDto clientRequestDto = new ClientDto().fromResponse(getClientResponse);
        clientRequestDto.setFirstName(clientDto.getFirstName()+"Edited");

        Response responsePut = RestAssuredClients.editById(clientDto.getId(),clientRequestDto,200);
        Assert.assertEquals(responsePut.getStatusCode(),200);
    }

    @Test(description = "Delete Client By Id")
    public void test05_Del_ClientById(){
        ClientDto clientDto = new ClientDto().init();

        Response postClientResponse = RestAssuredClients.post(clientDto,200);
        Assert.assertEquals(postClientResponse.getStatusCode(),200);

        Response deleteClientResponse = RestAssuredClients.deleteById(clientDto.getId(),200);
        Assert.assertEquals(deleteClientResponse.getStatusCode(),200);

        Response getClientResponse  = RestAssuredClients.getById(clientDto.getId(),200);

        ResponseDto retrieveResponse = new ResponseDto(getClientResponse);
        assertGroup.assertEquals(retrieveResponse.getID(),clientDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"FAILED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Client Coins By Id")
    public void test06_Put_Client_Coins(){
        ClientDto clientDto = new ClientDto().init();
        Response postClientResponse = RestAssuredClients.post(clientDto,200);
        Assert.assertEquals(postClientResponse.getStatusCode(),200);

        Response response  = RestAssuredClients.updateCoinsById(clientDto.getId(),5,200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),clientDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Coins has been updated successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Client By Id")
    public void test07_Put_InactiveClientBy(){
        ClientDto clientDto = new ClientDto().init();
        Response postClientResponse = RestAssuredClients.post(clientDto,200);
        Assert.assertEquals(postClientResponse.getStatusCode(),200);
        pause();
        Response response  = RestAssuredClients.inactiveClient(clientDto.getId(),200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),clientDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Active has been updated successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Client By Id")
    public void test08_Put_ActiveClientBy(){
        ClientDto clientDto = new ClientDto().init();
        Response postClientResponse = RestAssuredClients.post(clientDto,200);
        Assert.assertEquals(postClientResponse.getStatusCode(),200);
        pause();
        Response response  = RestAssuredClients.activeRecord(clientDto.getId(),200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),clientDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Active has been updated successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Client Email By Id")
    public void test09_Put_InactiveClientEmailBy(){
        ClientDto clientDto = new ClientDto().init();
        Response postClientResponse = RestAssuredClients.post(clientDto,200);
        Assert.assertEquals(postClientResponse.getStatusCode(),200);
        pause();
        Response response  = RestAssuredClients.inactiveEmail(clientDto.getId(),200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),clientDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Email Verified has been updated successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Client email By Id")
    public void test10_Put_ActiveClientEmailBy(){
        ClientDto clientDto = new ClientDto().init();
        Response postClientResponse = RestAssuredClients.post(clientDto,200);
        Assert.assertEquals(postClientResponse.getStatusCode(),200);
        pause();
        Response response  = RestAssuredClients.activeEmail(clientDto.getId(),200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),clientDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Email Verified has been updated successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Client Phone Token")
    public void test11_Put_ClientPhoneToken(){
        ClientDto clientDto = new ClientDto().init();
        Response postClientResponse = RestAssuredClients.post(clientDto,200);
        Assert.assertEquals(postClientResponse.getStatusCode(),200);
        pause();
        Response response  = RestAssuredClients.updatePhoneToken(clientDto.getId(),"9999999-9999999-9999999-9999-0-88",200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),clientDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Phone Token has been updated successfully.");
        assertGroup.assertAll();
    }

}
