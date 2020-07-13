package demo;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class TestExamplesLocalhost {

    @BeforeClass
    public void setUp(){
        baseURI = "http://localhost:3000/";
    }

    @Test
    public void test_get_users(){

        given()
                .get("/users")
        .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void test_get_subjects(){

        given()
                .param("name","Automation")
                .get("/subjects")
        .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void test_post(){
        JSONObject request = new JSONObject();

        request.put("firstName","Leo");
        request.put("lastName","White");
        request.put("subjectId",1);

        //Should create a new resource information
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Content-Type","application/json")
                .body(request.toJSONString())
        .when()
                .post("/users")
        .then()
                .statusCode(201)//Creation Status Code
                .log().all();
    }

    @Test
    public void test_batch(){
        JSONObject request = new JSONObject();

        request.put("lastName","Black");

        //Should create a new resource information
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Content-Type","application/json")
                .body(request.toJSONString())
         .when()
                .patch("/users/4")  //Update lastName on record 4
         .then()
                .statusCode(200)//Status Code
                .log().all();
    }

    @Test
    public void test_put(){
        JSONObject request = new JSONObject();

        request.put("firstName","Jane");
        request.put("lastName","Doe");
        request.put("subjectId",1);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Content-Type","application/json")
                .body(request.toJSONString())
        .when()
                .put("/users/4")
         .then()
                .statusCode(200)//Creation Status Code
                .log().all();
    }

    @Test
    public void test_delete(){

        when()
                .delete("/users/4")
        .then()
                .statusCode(204)
                .log().all();
    }

}
