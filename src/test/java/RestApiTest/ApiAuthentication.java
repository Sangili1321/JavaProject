package RestApiTest;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class ApiAuthentication {

    @Test
    public void BasicAuth(){
        RestAssured.given()
                .baseUri("https")
                .auth().basic("username", "password")
                .when()
                .get("/api/resource")
                .then().log().all();
    }

    @Test
    public void BearerToken(){
        RestAssured.given()
                .baseUri("https")
                .header("Authorization","Bearer " + "eyJHUH5676HJBJJNJN....")
                .when()
                .get("/api/resource")
                .then().log().all().statusCode(200);
    }
}
