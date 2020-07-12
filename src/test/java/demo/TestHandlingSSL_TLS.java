package demo;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestHandlingSSL_TLS {

    @Test
    public void testSSL(){
        given().get("http://www.bupa.com.aux").then().statusCode(200);
    }
}
