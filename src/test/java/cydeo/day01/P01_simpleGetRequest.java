package cydeo.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_simpleGetRequest {

    String url= "http://3.86.115.121:8000/api/spartans";

    @Test
    public void simpleGetRequest() {

        Response response = RestAssured.get(url);

        System.out.println("response.statusCode() = " + response.statusCode());

        //verify status code is 200
        int actualStatusCode = response.statusCode();

        //assert status is 200
        Assertions.assertEquals(200, actualStatusCode);

        //print jason response body
        response.prettyPrint();
    }
}
