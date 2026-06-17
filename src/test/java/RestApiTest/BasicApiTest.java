package RestApiTest;


import Pojos.Product;
import Pojos.ProductList;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiUtility;

import java.util.HashMap;
import java.util.*;

import static org.hamcrest.Matchers.*;

public class BasicApiTest {

    @Test
    public void testUser(){
        Response response = RestAssured
                .given()
                .baseUri("https://automationexercise.com")
                .when()
                .get("/api/productsList")
                .then()
                .extract().response();

        ProductList products = ApiUtility.getResponseAs(response, ProductList.class);
        List<ProductList> productsList = ApiUtility.getResponseAsList(response, new TypeRef<List<ProductList>>() {
        });

        Assert.assertEquals(response.getStatusCode(),200);
        //Assert.assertEquals(products.getProducts().get(0).getBrand(),"Polo");
        response.then().log().all();
}
@Test
public void testAndGetUserStatusCode(){
        RestAssured.given().baseUri("https://api.example.com")
                .when().get("/api/users/2")
                .then()
                .log().all().statusCode(200);
}

@Test()
public void testPostRequestWithBody(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("Name","Sangili");
        map.put("Designation","Senior Analyst");

        RestAssured.given().baseUri("https://api.example.com")
                .and().body(map).contentType("application/json")
                .when().post("/api/users")
                .then().log().all().statusCode(201);
}

@Test
public void testAddHeaders(){
       RestAssured.given().header("Authorization","Bearer","Accept","application/json")
               .baseUri("https://api.example.com")
               .when().get("/api/users").then().statusCode(200);
}

@Test
public void testResponseTime(){
        RestAssured.given().header("something","something")
                .when().get("uri")
                .then().time(lessThan(2000L));
}

@Test
public void testFieldExistence(){

        /*Inline Body assertions -> it comes with cleaner bdd style
        in one fluent chain itself you can validate the headers, time and body
        it is for validation not ideal candidate for the extraction of data
        */
        RestAssured.given().baseUri("uri")
                .when().get("restUri")
                .then().body("data",hasKey("id"))//validating whether the particular json has id key
                .body("data.id",notNullValue())//validating whether the id property has not null value
                .body("data[0].id", equalTo("1234"));//if it is array of json

    /*JsonPath -> it needs manual object creation for asserting
    It is best for extracting the id or something from one response
    and put in another response
    Easy for debugging you can print without any issue
    */
        JsonPath jPath = RestAssured.given().baseUri("uri")
            .when().get("restUri")
            .then().extract().jsonPath();
        Assert.assertEquals(jPath.getInt("data[1].id"),123);
        Assert.assertTrue(jPath.getBoolean("data[0].isSubscriber"));
}
@Test
    public void testPutRequestWithBody(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("Name","Durai");
        map.put("Designation","Senior Analyst");

        RestAssured.given().baseUri("https://api.example.com")
                .and().body(map).contentType("application/json")
                .when().put("/api/users")
                .then().log().all().statusCode(200);

    }
    @Test
    public void testPatchRequestWithBody(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("Name","Durai");
        map.put("Designation","Senior Analyst");

        RestAssured.given().baseUri("https://api.example.com")
                .and().body(map).contentType("application/json")
                .when().patch("/api/users")
                .then().log().all().statusCode(200);
    }
    public void testPaginationHandling(){

        int targetPage = 8;
        RestAssured.given().baseUri("baseUri")
                .queryParam("page", targetPage)
                .queryParam("per_page", 4)
                .when()
                .get("restUri")
                .then()
                .statusCode(200)
                .body("page",equalTo(targetPage))
                .body("per_page",equalTo(4))
                .body("data.size()", equalTo(7))//exactly like arraylist
                .body("data.size()", lessThan(100))
                .body("data.size()", greaterThan(98));
    }

    @Test
    public void SendDeleteRequest(){
        RestAssured
                .given()
                .baseUri("baseUri")
                .when().delete("/api/users/user3")
                .then().statusCode(204).statusCode(200);
        //It may return 204 no content or 200 ok status
    }
    @Test
    public void validateHeader(){
        RestAssured
                .given()
                .baseUri("baseUri")
                .when().get("/api/users/user3")
                .then().statusCode(200)
                .header("content-type","application/json")
                .header("server",equalTo("cloudFlare"));
    }
    @Test
    public void validateCookies(){
        RestAssured
                .given()
                .baseUri("baseUri")
                .when().get("/api/users/user3")
                .then().statusCode(200)
                .cookie("Cookie1","Value1")//validate the cookie
                .log().cookies();//log all cookies

        var cookies = RestAssured
                .given()
                .baseUri("baseUri")
                .when().get("/api/users/user3")
                .then().extract().cookies();
        //it will return the cookies in key value pair
        for(var v : cookies.entrySet()){
            System.out.println("Cookie Name : " +v.getKey() +", Cookie Value : " +v.getValue());
        }
    }

    //when endpoint expects application/x-www-form-urlencoded instead of raw JSON payloads
    @Test
    public  void sendFormParams(){
        RestAssured.given()
                .baseUri("baseUri")
                .formParam("username","sangili.a")
                .formParam("password","asd123")
                .when().get("restUri")
                .then().statusCode(200);
    }

    //To extract specific data from the response using path method
    @Test
    public  void ExtractSpecificData(){
        var designation = RestAssured.given()
                .baseUri("baseUri")
                .formParam("username","sangili.a")
                .formParam("password","asd123")
                .when().get("restUri")
                .then().statusCode(200).extract().path("data[1].designation");
        Assert.assertEquals(designation,"senior analyst");
    }


}
