package com.blacksystem.automation.application.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.blacksystem.automation.application.common.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import java.util.Arrays;

public class ExtentITestListener implements ITestListener {
    public static Logger logger = LogManager.getLogger();

    public static ExtentReports extent = ExtendReportManager.createInstance("extent.html");
    public static ExtentTest test;
    //Thread Safe for Test Execution in Parallel
    private final static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        String testName = null;

        if(result.getTestName() == null){
           String[] segments = result.getTestClass().getName().replace('.','/').split("/");
            testName = segments[segments.length-1];
        }else{
            testName = result.getTestClass().getTestName();
        }

        test = extent.createTest("Test: "+testName+" | Method: "+result.getMethod().getMethodName());
        extentTest.set(test);

        getDescriptionAttribute(result);

        if(result.getMethod().getPriority() > 0){
            String logText = "<strong style=\"font-weight: bolder;\">Priority</strong>: "+result.getMethod().getPriority();
            Markup markup = MarkupHelper.createLabel(logText, ExtentColor.TRANSPARENT);
            extentTest.get().log(Status.INFO,markup);
        }

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String logText = "<b>"+result.getMethod().getMethodName()+" - Successful</b>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.PURPLE);
        extentTest.get().log(Status.PASS,markup);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        String html = "<details>"+
                            "<summary>"+
                                "<b><font color=orange>Exception Stacktrace, click to see details:</font></b>"+
                            "</summary>"+
                            exceptionMessage.replaceAll(",","<br>")+
                        "</details>\n";

        String logText = "<i>"+result.getMethod().getMethodName()+"</i> - <span style=\"color:white;font-weight: bolder;font-size: 12px;\">Skipped<span>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.RED);
        extentTest.get().log(Status.FAIL,markup);
        extentTest.get().log(Status.FAIL,"Exception Message: "+result.getThrowable().getMessage());
        extentTest.get().fail(html);
        if(result.getThrowable().getCause()!=null){
            extentTest.get().log(Status.FAIL,"Cause By: "+result.getThrowable().getCause());
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        String html = "<details>"+
                "<summary>"+
                "<b><font color=orange>Exception Stacktrace, click to see details:</font></b>"+
                "</summary>"+
                exceptionMessage.replaceAll(",","<br>")+
                "</details>\n";

        String logText = "<i style=\"color:red;font-weight: bolder;font-size: 12px;\">"+result.getMethod().getMethodName()+" - </i> <span style=\"color:red;font-weight: bolder;font-size: 12px;\">Skipped<span>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
        extentTest.get().log(Status.SKIP,markup);

        extentTest.get().log(Status.SKIP,"Exception Message: "+result.getThrowable().getMessage());
        extentTest.get().skip(html);

        if(result.getThrowable().getCause()!=null){
            extentTest.get().log(Status.SKIP,"Cause By: "+result.getThrowable().getCause());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        //Generate Extent Report
        extent.flush();
    }

    /*
    *
    * */
    private void getDescriptionAttribute(ITestResult result){
        String logText = null;
        if(!result.getMethod().getDescription().equals("")){
            String[] descriptionAttributes = result.getMethod().getDescription().split(";");
            for (String value : descriptionAttributes){
                String[] text = value.split(":");
                String attribute = text[0].replaceAll("\\s+","").toLowerCase();

                if(attribute.equals("id")){
                    logger.info("JIRA TEST CASE ID: "+text[1]+"\n"+ Constants.SEPARATOR_DASH);
                    logText = "<strong style=\"font-weight: bolder;\">Test Case ID</strong>: <a target=\"_blank\" href=\"https://jira.equifax.com/browse/"+text[1].replaceAll("\\s+","")+"\">"+text[1]+"</a>";
                    Markup markup = MarkupHelper.createLabel(logText, ExtentColor.TRANSPARENT);
                    extentTest.get().log(Status.INFO,markup);
                }

                if(attribute.equals("description")){
                    logger.info("DESCRIPTION: "+text[1]);

                    logText = "<strong style=\"font-weight: bolder;\">Description</strong>: "+text[1];
                    Markup markup = MarkupHelper.createLabel(logText, ExtentColor.TRANSPARENT);
                    extentTest.get().log(Status.INFO,markup);
                }

                if(descriptionAttributes.length==1){
                    logger.info("DESCRIPTION: "+text[0]+"\n"+ Constants.SEPARATOR);
                    logText = "<strong style=\"font-weight: bolder;\">Description</strong>: "+text[0];
                    Markup markup = MarkupHelper.createLabel(logText, ExtentColor.TRANSPARENT);
                    extentTest.get().log(Status.INFO,markup);
                }
            }
        }
    }

}
