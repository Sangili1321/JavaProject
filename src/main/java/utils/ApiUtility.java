package utils;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

public class ApiUtility {

    public static <T> T getResponseAs(Response response, Class<T> tClass){
      return response.as(tClass);
    }

    public static <T> T getResponseAsList(Response response, TypeRef<T> typeRef){
        return response.as(typeRef);
    }
}
