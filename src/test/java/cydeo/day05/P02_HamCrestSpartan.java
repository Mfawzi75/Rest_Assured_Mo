package cydeo.day05;
import cydeo.utilities.SpartanTestBase;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class P02_HamCrestSpartan extends SpartanTestBase {

    /*
    Given accept type is Json
    And path param id is 15
    When user sends a get request to api/spartans/{id}
    Then status code is 200
    And content type is Json
    And json data has following
        "id": 15,
        "name": "Meta",
        "gender": "Female",
        "phone": 1938695106
     */

    @Test
    public void test1(){

        given()
                .accept(ContentType.JSON)
                .pathParam("id", 15)
        .when()
                .get("/api/spartans/{id}")
        .then()
                .statusCode(200)
                .contentType("application/json")
               // another way for assertion==> .statusCode(is(200));
                .body("id",is(15), "name",is("Meta"),"gender", equalTo("Female"),"phone", is(1938695106) );






    }

    @Test
    public void test2(){

        given()
                .accept(ContentType.JSON)
                .pathParam("id", 15)
        .when()
                .get("/api/spartans/{id}").prettyPeek()
        .then()
                .assertThat()         //synthetic sugar
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .assertThat()
                .body("id", is(15))
                .and()
                .body("name",is("Meta"))
                .body("gender",equalTo("Female"))
                .body("phone",is(1938695106));

    }

    @Test
    public void test3(){

        given()
                //.log().uri()
                //.log().ifValidationFails(LogDetail.URI)
                .log().ifValidationFails(LogDetail.HEADERS)
                .accept(ContentType.JSON)
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}").prettyPeek()//to print without break the chane
                .then()

                .assertThat()         //synthetic sugar
                //.log().status()

                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .assertThat()
                .body("id", is(15))
                .and()
                .body("name", is("Meta"))
                .body("gender", equalTo("Female"))
                .body("phone", is(1938695106));




    }

    @DisplayName("Using extract() method")
    @Test
    public void test4(){

        ExtractableResponse<Response> extract = given()
                //.log().uri()
                //.log().ifValidationFails(LogDetail.URI)
                .accept(ContentType.JSON)
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()

                .assertThat()         //synthetic sugar
                //.log().status()

                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .assertThat()
                .body("id", is(15))
                .and()
                .body("name", is("Meta"))
                .body("gender", equalTo("Female"))
                .body("phone", is(1938695106))
                .extract();

        Response response = extract.response();

        JsonPath jsonPath = extract.jsonPath();

       // response.prettyPrint();
       // jsonPath.prettyPrint();

        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());

        System.out.println("jsonPath.getString(\"name\") = " + jsonPath.getString("name"));
    }

}
