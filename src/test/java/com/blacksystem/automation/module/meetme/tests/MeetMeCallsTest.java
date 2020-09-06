package com.blacksystem.automation.module.meetme.tests;

import com.blacksystem.automation.application.common.BaseTest;
import com.blacksystem.automation.module.meetme.collections.RestAssuredCalls;
import com.blacksystem.automation.module.meetme.dtos.StartCallDto;
import org.testng.annotations.Test;

public class MeetMeCallsTest extends BaseTest {

    @Test
    public void test01_Post_StartCall(){
        StartCallDto startCallDto = new StartCallDto().init();
        RestAssuredCalls.post(startCallDto,200);
    }

}
