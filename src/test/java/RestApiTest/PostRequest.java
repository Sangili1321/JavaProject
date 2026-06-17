package RestApiTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.*;

import static org.hamcrest.Matchers.*;
public class PostRequest {

    //when the property is very less like 1 or 2
    @Test
    public void stringBodyRequest(){

        var body = "{\"Name\" : \"Chaos\", \"Designation\" : \"Senior Analyst\"}";
        var print = RestAssured.given().baseUri("https://reqres.in")
                .body(body)
                .contentType(ContentType.JSON)
                .when().post("/api/users")
                .then().extract().response().getBody().prettyPrint();
        System.out.println(print);
    }

    //when the property is static then you can go with this
   @Test
    public void fileBodyRequest(){

        var filePath = System.getProperty("user.dir") + "src\\test\\resources\\JsonFiles\\Details.json";
        File file = new File(filePath);
        RestAssured.given().baseUri("https://reqres.in")
                .body(file)
                .contentType(ContentType.JSON)
                .when().post("/api/users")
                .then().log().all().statusCode(201);
    }

    //Pojo example is present in BasicApiTest.java file
}
