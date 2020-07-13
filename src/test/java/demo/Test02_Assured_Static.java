package demo;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

//Static Import For RestAssured
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Test02_Assured_Static {

    @Test
    void test_001(){
        Response response = get("https://reqres.in/api/users?page=2");

        System.out.println("getStatusCode:"+response.getStatusCode());

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200,"[ERROR]: Status Code Incorrect ["+statusCode+"]");
    }

    @Test
    void test_002(){
        given()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data.id[0]",equalTo(7));
    }
}
