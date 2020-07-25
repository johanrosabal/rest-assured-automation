package com.blacksystem.automation.module.demo;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class TestAssured {

    @Test
    public void GetWeatherDetails() {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "http://3.13.86.142:3000/contacts/";

        // Get the RequestSpecification of the request that you want to sent
        // to the server. The server is specified by the BaseURI that we have
        // specified in the above step.
        RequestSpecification httpRequest = RestAssured.given();

        // Make a request to the server by specifying the method Type and the method URL.
        // This will return the Response from the server. Store the response in a variable.
        Response response = httpRequest.request(Method.GET);

        // Now let us print the body of the message to see what response
        // we have recieved from the server
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);
        System.out.println("Response Status Line is =>  " + response.getStatusLine());
        System.out.println("Response Status Code is =>  " + response.getStatusLine());
    }

}
