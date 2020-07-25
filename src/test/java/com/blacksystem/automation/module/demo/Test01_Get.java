package com.blacksystem.automation.module.demo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

//Using Open APIs for Testing https://reqres.in/

public class Test01_Get {
    @Test
    void test_001(){
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        response.getStatusCode();
        response.getBody();
        response.asString();

        System.out.println("asString: "+response.asString());
        System.out.println("asString:"+response.getBody().asString());
        System.out.println("getStatusCode:"+response.getStatusCode());
        System.out.println("getStatusLine:"+response.getStatusLine());
        System.out.println("getBody:"+response.getBody());
        System.out.println("getHeader:"+response.getHeader("content-type"));
        System.out.println("getTime:"+response.getTime());

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200,"[ERROR]: Status Code Incorrect ["+statusCode+"]");

    }

}
