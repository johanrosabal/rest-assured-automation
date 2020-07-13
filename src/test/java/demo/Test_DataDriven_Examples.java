package demo;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Test_DataDriven_Examples {

    @BeforeClass
    public void setUp(){
        baseURI = "http://localhost:3000/";
    }
    /*
    TestNG Data Provider
     */
    @DataProvider(name="DataForPost_1")
    public Object[][] dataForPost_1(){

        Object[][] data = new Object[2][3];

        data[0][0] = "Albert";
        data[0][1] = "Einstein";
        data[0][2] = 2;

        data[1][0] = "Tomas";
        data[1][1] = "Edison";
        data[1][2] = 2;

        return data;
    }

    @DataProvider(name="DataForPost_2")
    public Object[][] dataForPost_2(){
        return new Object[][]{
                {"Graham","Bell",1},
                {"Henry","Ford",2}
        };
    }
    //@Test(dataProvider = "DataForPost_2")
    public void test_post(String firstName, String lastName, int subjectId){

        JSONObject request = new JSONObject();

        request.put("firstName",firstName);
        request.put("lastName",lastName);
        request.put("subjectId",subjectId);

        //Run Test Post
        given()
                .headers("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
        .when()
                .post("/users")
        .then()
                .statusCode(201)
                .log().all(); // 201 Successful Creation
    }

    @DataProvider(name = "DeleteData")
    public Object[] dataForDelete(){
        return new Object[]{
                7
        };
    }

    /*
    * Deleting records using Object
    * */
    //@Test(dataProvider = "DeleteData")
    public void test_delete(int userId){
        when()
                .delete("/users/"+userId)
        .then()
                .statusCode(200) //204 No Content
                .log().all();
    }

    /*
    * Deleting sending parameter from XML Suite File
    * */
    @Parameters({"userId"})
    @Test
    public void test_delete_2(int userId){
        when()
                .delete("/users/"+userId)
                .then()
                .statusCode(200) //204 No Content
                .log().all();
    }
}
