package com.blacksystem.automation.module.meetme.tests;

import com.blacksystem.automation.application.common.BaseTest;
import com.blacksystem.automation.module.meetme.collections.RestAssuredCalls;
import com.blacksystem.automation.module.meetme.dtos.StartCallDto;
import com.blacksystem.automation.module.meetme.quicktype.Converter;
import com.blacksystem.automation.module.meetme.quicktype.ResponseMessages;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

public class MeetMeCallsValidationTest extends BaseTest {

    @Test
    public void test01_Post_StartCall_Validation() throws IOException {
        StartCallDto startCallDto = new StartCallDto();
        Response response = RestAssuredCalls.startCall(startCallDto,200);
        ResponseMessages responseMessages =  Converter.fromJsonString(response);

        assertGroup.assertEquals(responseMessages.getErrors().get(0).getClientID(),"Client Id value must be provided.");
        assertGroup.assertEquals(responseMessages.getErrors().get(1).getAgentID(),"Agent Id value must be provided.");
        assertGroup.assertEquals(responseMessages.getErrors().get(2).getType(),"Type of call value must be provided.");
        assertGroup.assertAll();
    }
}
