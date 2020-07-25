package com.blacksystem.automation.application.reports;

import com.blacksystem.automation.application.common.BaseTest;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExtentReportsTest extends BaseTest {

    @BeforeTest
    public void setUp(){
        addDashboardDetails("Additional Details 1","Some Details 1");
        addDashboardDetails("Additional Details 2","Some Details 2");
    }

    @Test(description = "This is a description for successful test.")
    public void testSuccessful(){
        logger.info("Executing Test Successful.");
        setStepTestCaseID("JRM-001");
        setStepURL("http://google.com");
        setStepDetails("Some Details");
        setStepDetailsHTML("User","username");
        setStepDetailsHTML("Password","12345678");
    }

    @Test(description = "description: This is a description for failed test.; id: AAA-002")
    public void testFail(){
        logger.info("Executing Test failed.");
        Assert.fail("Failed Method Test.");
    }

    @Test(description = "description: Skipped Test; id: AAA-999")
    public void testSkipped(){
        logger.info("Executing Test Skipped.");
        throw new SkipException("Executing Skipped Test Exception.");
    }

}
