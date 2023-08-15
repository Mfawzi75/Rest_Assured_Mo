package cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SpartanGetRequest {

    /*
     * Given content type is application/json
     * When user sends GET request /api/spartans endpoint
     * Then status code should be 200
     * And Content type should be application/json
     */

    String url= "http://3.86.115.121:8000";

    @Test

    public void GetAllSpartans(){

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get(url + "/api/spartans");

        //response.prettyPrint();

        int actualStatusCode = response.statusCode();

        Assertions.assertEquals(200, actualStatusCode);

        String actualContentType = response.contentType();

        Assertions.assertEquals("application/json", actualContentType);

        System.out.println("response.getHeader(\"Content-type\") = " + response.getHeader("Content-type"));
        System.out.println("response.header(\"Connection\") = " + response.header("Connection"));
        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        //verify header exist
        boolean isDateHeaderExist = response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(isDateHeaderExist);

    }

    @Test
    public void getOneSpartan(){
        /*
         * Given content type is application/json
         * When user sends GET request /api/spartans/3 endpoint
         * Then status code should be 200
         * And Content type should be application/json
         * And response body needs to contains Fidole
         */

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get(url + "/api/spartans/3");

        int actualStatusCode= response.statusCode();
        Assertions.assertEquals(200, actualStatusCode);

        String actualContentType=response.contentType();

        Assertions.assertEquals("application/json",actualContentType);

        response.body().prettyPrint();

        //this is not a good way to make assertion
       Assertions.assertTrue(response.body().asString().contains("Fidole"));


    }
    @Test
    public void helloSpartan(){

        /*
        Given no headers provided
        When Users send GET request to /api/hello
        Then response status code should be 200
        And Content type header should be "text/plain;charset=UTF-8"
        And header should contain Date
        And Content-Length should be 17
        And body should be "Hello from Sparta"
         */


        Response response = RestAssured.when().get(url + "/api/hello");

        int actStatusCode= response.statusCode();
        Assertions.assertEquals(200, actStatusCode);

        String actualContentType= response.contentType();
        Assertions.assertEquals("text/plain;charset=UTF-8",actualContentType);

        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        System.out.println(response.header("Content-Length"));
        Assertions.assertEquals("17", response.header("Content-Length"));

        System.out.println(response.body().asString());
        Assertions.assertEquals("Hello from Sparta", response.body().asString());
    }
}
