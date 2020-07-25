package com.blacksystem.automation.application.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExtendReportManager {

    public static ExtentReports extent;

    public static ExtentReports getInstance(){

        if(extent == null){
            createInstance("extent.html");
        }
        return extent;
    }
    public static ExtentReports createInstance(String fileName){

        new File(System.getProperty("user.dir")+"\\reports\\").mkdirs();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\reports\\"+fileName);


        System.out.println(htmlReporter.getFilePath());

        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Equifax - Automation Reports");
        htmlReporter.config().setDocumentTitle("Automation Test Results");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();

        extent.setSystemInfo("Company","Company Name");
        extent.setSystemInfo("Organization","Organization Name");
        extent.setSystemInfo("Automation","QA automation");

        extent.attachReporter(htmlReporter);

        return extent;
    }

    public static void addEnvironmentInformation(HashMap<String, String> info){
        int size = info.size();

        for (Map.Entry<String, String> entry : info.entrySet()) {
            extent.setSystemInfo(entry.getKey(),entry.getValue());
        }
    }
    private static String getReportName(){
        Date date = new Date();
        return "Automation_Report_"+ date.toString().replace(":","_").replace(" ","_")+".html";
    }
}
