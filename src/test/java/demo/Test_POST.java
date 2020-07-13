package demo;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Test_POST {

    @Test
    public void test_1_post(){
//        Map<String ,Object> map = new HashMap<>();
//        map.put("name","Johan");
//        map.put("job","QA Automation");
//
//        System.out.println(map);

        JSONObject request = new JSONObject();

        request.put("name","Johan");
        request.put("job","QA Automation");

        System.out.println(request);
        System.out.println(request.toJSONString());

        //Run Test Post
        given()
                .headers("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
        .when()
                .post("https://reqres.in/api/users")
        .then()
                .statusCode(201)
                .log().all(); // 201 Successful Creation
    }
}
