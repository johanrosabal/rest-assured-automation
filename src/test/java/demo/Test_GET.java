package demo;


//Using Open APIs for Testing https://reqres.in/

/*
* Installing Json Server with Node Js for LocalHost Server with Customize API for Testing
* run: npm install -g json-server
* start server: json-server --watch db.json
* https://github.com/typicode/json-server
* */

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Test_GET {

    @Test
    public void test_1(){
        given()
                .get("https://reqres.in/api/users?page=2")
        .then()
                .statusCode(200)
                .body("data.id[1]",equalTo(8))
                .log().all();
    }

    @Test
    public void test_2(){
        given()
                .header("Content-Type","application/json")
                .get("https://reqres.in/api/users?page=2")
        .then()
                .statusCode(200)
                .body("data.id[1]",equalTo(8))
                .body("data.first_name",hasItems("Michael","Lindsay"));
    }

}
