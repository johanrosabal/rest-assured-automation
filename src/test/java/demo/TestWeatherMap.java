package demo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestWeatherMap {

    @Test
    public void responseCode(){
        Response response = RestAssured.get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=439d4b804bc8187953eb36d2a8c26a02");
        int code = response.getStatusCode();
        System.out.println("Status Code is "+code);
        Assert.assertEquals(code,200);
    }
}
