package com.blacksystem.automation.module.meetme.tests;

import com.blacksystem.automation.application.common.BaseTest;
import com.blacksystem.automation.module.meetme.collections.RestAssuredClients;
import com.blacksystem.automation.module.meetme.dtos.ClientDto;
import com.blacksystem.automation.module.meetme.dtos.ClientErrorsDto;
import com.blacksystem.automation.module.meetme.dtos.ClientsDto;
import com.blacksystem.automation.module.meetme.dtos.ResponseDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class MeetMeClientsTest extends BaseTest {

    @Test(description = "Add 1s new clients")
    public void test01_Post_AddNewClient(){
        List<Response> responses = RestAssuredClients.postClients(1,200,true);

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

        Response responsePost = RestAssuredClients.postClient(clientDto,200);
        Assert.assertEquals(responsePost.getStatusCode(),200);

        Response responseGet  = RestAssuredClients.getClientById(clientDto.getId(),200);
        Assert.assertEquals(responseGet.getStatusCode(),200);

        ClientDto clientResponseDto = new ClientDto().fromResponse(responseGet);
        clientResponseDto.setId(clientDto.getId());

        Assert.assertEquals(clientResponseDto,clientDto);
    }

    @Test(description = "Get All Customers")
    public void test03_Get_AllClients(){
        Response responseGet = RestAssuredClients.getAllClients(200);
        Assert.assertEquals(responseGet.getStatusCode(),200);

        ClientsDto clients = new ClientsDto(responseGet);

        if(clients.getClients().size() == 0){
            Assert.fail("[ERROR]: Get All Clients should not be 0.");
        }
    }

    @Test(description = "Edit Client By Id")
    public void test04_Put_EditClientById(){
        ClientDto clientDto = new ClientDto().init();

        Response postClientResponse = RestAssuredClients.postClient(clientDto,200);
        Assert.assertEquals(postClientResponse.getStatusCode(),200);

        Response getClientResponse  = RestAssuredClients.getClientById(clientDto.getId(),200);

        ClientDto clientRequestDto = new ClientDto().fromResponse(getClientResponse);
        clientRequestDto.setFirstName(clientDto.getFirstName()+"Edited");

        Response responsePut = RestAssuredClients.editClientById(clientDto.getId(),clientRequestDto,200);
        Assert.assertEquals(responsePut.getStatusCode(),200);
    }

    @Test(description = "Delete Client By Id")
    public void test05_Del_ClientById(){
        ClientDto clientDto = new ClientDto().init();

        Response postClientResponse = RestAssuredClients.postClient(clientDto,200);
        Assert.assertEquals(postClientResponse.getStatusCode(),200);

        Response deleteClientResponse = RestAssuredClients.deleteClientById(clientDto.getId(),200);
        Assert.assertEquals(deleteClientResponse.getStatusCode(),200);

        Response getClientResponse  = RestAssuredClients.getClientById(clientDto.getId(),200);

        ResponseDto retrieveResponse = new ResponseDto(getClientResponse);
        assertGroup.assertEquals(retrieveResponse.getID(),clientDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"FAILED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Client Coins By Id")
    public void test06_Put_Client_Coins(){
        ClientDto clientDto = new ClientDto().init();
        Response postClientResponse = RestAssuredClients.postClient(clientDto,200);
        Assert.assertEquals(postClientResponse.getStatusCode(),200);

        Response response  = RestAssuredClients.updateCoinsClientById(clientDto.getId(),5,200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),clientDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Coins has been updated successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Client By Id")
    public void test07_Put_InactiveClientBy(){
        ClientDto clientDto = new ClientDto().init();
        Response postClientResponse = RestAssuredClients.postClient(clientDto,200);
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
        Response postClientResponse = RestAssuredClients.postClient(clientDto,200);
        Assert.assertEquals(postClientResponse.getStatusCode(),200);
        pause();
        Response response  = RestAssuredClients.activeClient(clientDto.getId(),200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),clientDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Active has been updated successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Client Email By Id")
    public void test09_Put_InactiveClientEmailBy(){
        ClientDto clientDto = new ClientDto().init();
        Response postClientResponse = RestAssuredClients.postClient(clientDto,200);
        Assert.assertEquals(postClientResponse.getStatusCode(),200);
        pause();
        Response response  = RestAssuredClients.inactiveClientEmail(clientDto.getId(),200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),clientDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Email Verified has been updated successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Client email By Id")
    public void test10_Put_ActiveClientEmailBy(){
        ClientDto clientDto = new ClientDto().init();
        Response postClientResponse = RestAssuredClients.postClient(clientDto,200);
        Assert.assertEquals(postClientResponse.getStatusCode(),200);
        pause();
        Response response  = RestAssuredClients.activeClientEmail(clientDto.getId(),200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),clientDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Email Verified has been updated successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Client Phone Token")
    public void test11_Put_ClientPhoneToken(){
        ClientDto clientDto = new ClientDto().init();
        Response postClientResponse = RestAssuredClients.postClient(clientDto,200);
        Assert.assertEquals(postClientResponse.getStatusCode(),200);
        pause();
        Response response  = RestAssuredClients.updateClientPhoneToken(clientDto.getId(),"9999999-9999999-9999999-9999-0-88",200);

        ResponseDto retrieveResponse = new ResponseDto(response);
        assertGroup.assertEquals(retrieveResponse.getID(),clientDto.getId());
        assertGroup.assertEquals(retrieveResponse.getStatus(),"COMPLETED");
        assertGroup.assertEquals(retrieveResponse.getMessage(),"Phone Token has been updated successfully.");
        assertGroup.assertAll();
    }

    @Test(description = "Post Empty Client Verify Required Fields")
    public void test12_Post_Client_Validation(){
        ClientDto clientDto = new ClientDto();

        Response response  = RestAssuredClients.postClient(clientDto,200);

        ClientErrorsDto errors = new ClientErrorsDto(response);
        assertGroup.assertEquals(errors.getId(),"field must be provided.");
        assertGroup.assertEquals(errors.getFirstName(),"First name must be provided.");
        assertGroup.assertEquals(errors.getLastName(),"Last name must be provided.");
        assertGroup.assertEquals(errors.getSecondLastName(),"Second last name must be provided.");
        assertGroup.assertEquals(errors.getBirthDate(),"Cannot read property 'split' of undefined");
        assertGroup.assertEquals(errors.getGender(),"Gender must be provided.");
        assertGroup.assertEquals(errors.getNickName(),"Nickname must be provided.");
        assertGroup.assertEquals(errors.getEmail(),"Is not a valid email.");
        assertGroup.assertEquals(errors.getLanguage(),"Language must be provided.");
        assertGroup.assertEquals(errors.getCountry(), "Country Code must be provided.");
        assertGroup.assertAll();
    }

    @Test(description = "Get Client By Id when Client is not exist")
    public void test13_Get_ClientById_Validation(){
        ClientDto clientDto = new ClientDto().init();
        Response responseGet  = RestAssuredClients.getClientById(clientDto.getId(),200);
        Assert.assertEquals(responseGet.getStatusCode(),200);

        ResponseDto response = new ResponseDto(responseGet);
        assertGroup.assertEquals(response.getID(),clientDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Edit Client By Id when client not exist")
    public void test14_Put_EditClientById_Validation(){
        ClientDto clientDto = new ClientDto().init();
        Response responseGet  = RestAssuredClients.editClientById(clientDto.getId(),clientDto,200);
        Assert.assertEquals(responseGet.getStatusCode(),200);

        ResponseDto response = new ResponseDto(responseGet);
        assertGroup.assertEquals(response.getID(),clientDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Delete Client By Id when client not exist")
    public void test15_Del_ClientById_Validation(){
        ClientDto clientDto = new ClientDto().init();
        Response responseGet  = RestAssuredClients.deleteClientById(clientDto.getId(),200);
        Assert.assertEquals(responseGet.getStatusCode(),200);

        ResponseDto response = new ResponseDto(responseGet);
        assertGroup.assertEquals(response.getID(),clientDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Client Coins By Id when Client is not exist")
    public void test16_Put_Client_Coins_Validation(){
        ClientDto clientDto = new ClientDto().init();

        Response putCoinsResponse  = RestAssuredClients.updateCoinsClientById(clientDto.getId(),5,200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getID(),clientDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Client By Id when Client is not exist")
    public void test17_Put_InactiveClientBy_Validation(){
        ClientDto clientDto = new ClientDto().init();

        Response putCoinsResponse  = RestAssuredClients.inactiveClient(clientDto.getId(),200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getID(),clientDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Client By Id when Client is not exist")
    public void test18_Put_ActiveClientBy_Validation(){
        ClientDto clientDto = new ClientDto().init();

        Response putCoinsResponse  = RestAssuredClients.activeClient(clientDto.getId(),200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getID(),clientDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Client Email By Id when Client is not exist")
    public void test19_Put_InactiveClientEmailBy_Validation(){
        ClientDto clientDto = new ClientDto().init();

        Response putCoinsResponse  = RestAssuredClients.inactiveClientEmail(clientDto.getId(),200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getID(),clientDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Inactive Client email By Id when Client is not exist")
    public void test20_Put_ActiveClientEmailBy_Validation(){
        ClientDto clientDto = new ClientDto().init();

        Response putCoinsResponse  = RestAssuredClients.activeClientEmail(clientDto.getId(),200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getID(),clientDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }

    @Test(description = "Update Client Phone Token when Client is not exist")
    public void test21_Put_ClientPhoneToken_Validation(){
        ClientDto clientDto = new ClientDto().init();

        Response putCoinsResponse  = RestAssuredClients.updateClientPhoneToken(clientDto.getId(),"9999999-9999999-9999999-9999-0-88",200);

        ResponseDto response = new ResponseDto(putCoinsResponse);
        assertGroup.assertEquals(response.getID(),clientDto.getId());
        assertGroup.assertEquals(response.getStatus(),"FAILED");
        assertGroup.assertEquals(response.getMessage(),"Record not found.");
        assertGroup.assertAll();
    }









}
