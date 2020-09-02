package com.blacksystem.automation.application.common;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.blacksystem.automation.application.dto.DashboardInformationDto;
import com.blacksystem.automation.application.properties.ContextProperties;
import com.blacksystem.automation.application.reports.ExtendReportManager;
import com.blacksystem.automation.application.reports.ExtentITestListener;
import io.restassured.RestAssured;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.lang.reflect.Method;
import java.util.HashMap;

@Listeners(ExtentITestListener.class)
public class BaseTest {

    public static Logger logger = LogManager.getLogger();
    protected static final Level LOG_BASE_TEST = Level.forName("BASE_TEST",451);
    public Response response;

    public SoftAssert assertGroup;

    //Share Dto Object for adding Environment Information to the Dashboard of Extent Report
    //Set Information on Test SetUp Before Test
    public DashboardInformationDto info = new DashboardInformationDto();

    //Share Has Map for Additional Details
    public HashMap<String,String> additionalInfo = new HashMap<>();

    /*-[Private Attributes]--------------------------------------------------*/
    public static String baseUrl;

    @BeforeSuite(alwaysRun = true)
    protected void beforeSuite001(ITestContext context){
        logger.log(LOG_BASE_TEST,"Setup Before Suite 001");
        //Initialize Context Properties
        ContextProperties.setProperties(context);

        baseUrl = ContextProperties.getBaseUrl();

        //Setting Logger Root Level Run Time
        Configurator.setRootLevel(ContextProperties.getLevel());

        logger.info("Log Level: "+ContextProperties.getLogLevel());

        RestAssured.baseURI = "http://localhost:5001/meet-me-core-api/us-central1/app";
        //RestAssured.baseURI = "https://us-central1-meet-me-core-api.cloudfunctions.net/app";
        //RestAssured.filters(ResponseLoggingFilter.responseLogger(),new ResponseLoggingFilter());
    }

    /*------------------------------[Suite Events]------------------------------*/
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite002(ITestContext context){
        logger.log(LOG_BASE_TEST,"Setup Before Suite 002");
        logger.info("Parallel Mode: "+ ContextProperties.getParallelMode());

        if(ContextProperties.getParallelMode().equalsIgnoreCase("none") ||
                ContextProperties.getParallelMode().equalsIgnoreCase("false") ||
                ContextProperties.getParallelMode().equalsIgnoreCase("true")){

            logger.log(LOG_BASE_TEST,"Parallel Mode: "+ContextProperties.getParallelMode());

        }
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite001(){
        logger.log(LOG_BASE_TEST,"After Suite");
        if(ContextProperties.getParallelMode().equalsIgnoreCase("none") ||
                ContextProperties.getParallelMode().equalsIgnoreCase("false") ||
                ContextProperties.getParallelMode().equalsIgnoreCase("true")){
            //Here Some Procedure After Suite Execute
        }
    }

    /*------------------------------[Class Events]------------------------------*/
    @BeforeClass(alwaysRun = true)
    public void beforeClass001(ITestContext context){
        if(ContextProperties.getParallelMode().equalsIgnoreCase("classes")){
            logger.log(LOG_BASE_TEST,"Before Class");
            logger.log(LOG_BASE_TEST,"Init Driver: "+ContextProperties.getParallelMode());
            //Here Some Procedure Before Class
        }
    }

    @AfterClass(alwaysRun = true)
    public void afterClass001(ITestContext context){
        if(ContextProperties.getParallelMode().equalsIgnoreCase("classes")){
            logger.log(LOG_BASE_TEST,"After Class");
            logger.log(LOG_BASE_TEST,"Closing Driver");
            //Here Some Procedure After Class
        }
    }

    /*------------------------------[Method Events]------------------------------*/
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod001(ITestContext context){
        response = null;
        if(ContextProperties.getParallelMode().equalsIgnoreCase("methods")){
            logger.log(LOG_BASE_TEST,"Before Method");
            logger.log(LOG_BASE_TEST,"Parallel Mode: "+ContextProperties.getParallelMode());
            //Here Some Procedure Before Method
        }
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod001(){
        logger.log(LOG_BASE_TEST,"After Method");
        if(ContextProperties.getParallelMode().equalsIgnoreCase("methods")){
            logger.log(LOG_BASE_TEST,"Closing Browser");
            //Here Some Procedure After Method
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod002(Method method){
        logger.info("TEST STARTED: " + getClass().getName()+ "."+method.getName());
        assertGroup = new SoftAssert();
    }

    @AfterMethod (alwaysRun = true)
    public void afterMethod002(ITestResult result, Method method){

        switch (result.getStatus()){
            case ITestResult.SUCCESS:
                logger.info("TEST PASSED: " + this.getClass().getName()+ "." + method.getName()+"\n"+ Constants.SEPARATOR);
                break;
            case ITestResult.FAILURE:
                logger.info("TEST FAILED: " + this.getClass().getName()+ "." + method.getName()+"\n"+Constants.SEPARATOR);
                break;
            case ITestResult.SKIP:
                logger.info("TEST SKIPPED: " + this.getClass().getName()+ "." + method.getName()+"\n"+Constants.SEPARATOR);
                break;
            default:
                logger.error("ITest Result not identify: "+result.getStatus());
        }

    }

    /*------------------------------[Test Events]------------------------------*/
    @BeforeTest(alwaysRun = true)
    public void beforeTest001(ITestContext context){
        if(ContextProperties.getParallelMode().equalsIgnoreCase("tests")){
            logger.log(LOG_BASE_TEST,"Before Test");
            logger.log(LOG_BASE_TEST,"Init Driver: "+ContextProperties.getParallelMode());
            //Before Procedure Test Execution
        }

    }

    @AfterTest(alwaysRun = true)
    public void afterTest001(ITestContext context){
        if(ContextProperties.getParallelMode().equalsIgnoreCase("tests")){
            logger.log(LOG_BASE_TEST,"After Test");
            logger.debug("Close Driver");
            //After Procedure Test Execution
        }
    }

    /**
     * This method is used for Describe the Test Case Description in the log
     */

    @AfterTest
    public void afterTest002_addNewEnvironmentInformation(){
        //After Test Information Add Dashboard Information
        ExtendReportManager.addEnvironmentInformation((HashMap<String, String>) info.toMap());

        if(additionalInfo.size()>0){
            ExtendReportManager.addEnvironmentInformation(additionalInfo);
        }
    }

    /**
     * Set a skip condition for a specific scenario to evaluate
     * @param skipMessage Message for Precondition to evaluate
     * @param method This automatically call by execution runtime
     */
    public void setSkipCondition(String skipMessage, Method method){
        logger.info("SKIP DESCRIPTION: "+skipMessage+"\n"+Constants.SEPARATOR);
        throw new SkipException(this.getClass().getName()+method.getName()+ ": " + skipMessage);
    }

    // Extent Reports Methods

    /*
     * Set Step Details Plain Text Message by Test
     * */
    public void setStepDetails(String message){
        ExtentITestListener.test.info(message);
        logger.info(message);
    }

    /*
     * Set Step Details Plain Text Message by Test
     * */
    public void setStepDetails(String attribute, String value){
        ExtentITestListener.test.info(attribute + ": " + value);
        logger.info(attribute + ": " + value);
    }

    /*
     * Set Step Details Plain Text Message by Test
     * */
    public void setStepDetails(String attribute, int value){
        ExtentITestListener.test.info(attribute + ": " + String.valueOf(value));
        logger.info(attribute + ": " + value);
    }

    /*
     * Set Step Details HTML Format Message by Test, with field and value
     * */
    public void setStepURL(String message){
        String logText = "<strong style\"font-weight: bolder;\" > URL<strong>: <a target=\"_blank\" href=\""+message.replaceAll("\\s+","")+"\">"+message;
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.TRANSPARENT);
        ExtentITestListener.test.info(markup);
    }

    /*
     * Set Step Details HTML Format Message by Test, with field and value
     * */
    public void setStepTestCaseID(String message){
        String jiraDomain = ContextProperties.getJiraDomain();
        String logText = "<strong style=\"font-weight: bolder;\">Test Case ID</strong>: <a target=\"_blank\" href=\""+jiraDomain+"/browse/"+message.replaceAll("\\s+","")+"\">"+message+"</a>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.TRANSPARENT);
        ExtentITestListener.test.info(markup);
    }

    /*
     * Set Step Details HTML Format Message by Test, with field and value
     * */
    public void setStepDetailsHTML(String field, String message){
        String logText = "<strong style\"font-weight: bolder;\" > "+field+"</strong>: "+message;
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.TRANSPARENT);
        ExtentITestListener.test.info(markup);
    }

    /*
     * Add Dashboard Details Message by Test
     * */
    public void addDashboardDetails(String name, String value){
        additionalInfo.put(name,value);
    }

    /*
     * Get Response Body
     * */
    public static void getResponse(Response response){
        String message = response.getBody().asString();
        String logText = "<span style=\"color: black;\"><strong style=\"font-weight: bolder;\">Response Body</strong>: "+message+"</span>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
        ExtentITestListener.test.info(markup);
        logger.info("Response: "+ message);
    }

    /*
     * End Point URL
     * */
    public static void getEndPoint(String url){

        String logText = "<span style=\"color: black;\"><strong style=\"font-weight: bolder;\">End Point</strong>: "+url+"</span>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
        ExtentITestListener.test.info(markup);
        logger.info("Endpoint: "+ url);
    }

    /*
     * Get Status Code From Response
     * */
    public static void getStatusCode(Response response){
        String message = response.getStatusCode() + " - " + response.getStatusLine();
        String logText = "<span style=\"color: black;\"><strong style=\"font-weight: bolder;\">Status Code</strong>: "+message+"</span>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.LIME);
        ExtentITestListener.test.info(markup);
        logger.info("Status Code: "+ message);
    }

    /*
     * Get Status Code From Response
     * */
    public static void getRequestBody(String message){
        String logText = "<span style=\"color: black;\"><strong style=\"font-weight: bolder;\">Request Body</strong>: <code>"+message+"</code></span>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.LIME);
        ExtentITestListener.test.info(markup);
        logger.info("Request Body: "+ message);
    }

    /*
     * Get Response Time
     * */
    public static void getResponseTime(Response response){
        String message = String.valueOf(response.getTime());
        String logText = "<span style=\"color: black;\"><strong style=\"font-weight: bolder;\">Response Time</strong>: "+message+"</span>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.AMBER);
        ExtentITestListener.test.info(markup);
        logger.info("Response Time: "+ message);
    }

    public static void pause(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
