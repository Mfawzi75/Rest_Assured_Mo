package cydeo.day03;

import cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_SpartanWithResponsePath extends SpartanTestBase {

      /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "/api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
   */

    @Test
    public void task1(){
        Response response= given().accept(ContentType.JSON)
                .and()
                .pathParam("id",10)
                .when()
                .get("/api/spartans/{id}");

       // response.prettyPrint();
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals("application/json", response.contentType());

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone= response.path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(10, id);
        assertEquals("Lorenza",name);
        assertEquals("Female", gender);
        assertEquals(3312820936l, phone);


    }

    @Test
    public void task2(){

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/api/spartans");

       // response.prettyPrint();

        //get first spartan ID
        int firstID = response.path("[0].id");
        System.out.println("firstID = " + firstID);
        //or:
        int IdFirst = response.path("id[0]");
        System.out.println("IdFirst = " + IdFirst);

        //get first name
        String firstName = response.path("[0].name");
        System.out.println("firstName = " + firstName);

        //get second name
        String secondName = response.path("[1].name");
        System.out.println("secondName = " + secondName);
        
        //get all names
        List<String> allNames = response.path("name");

        //print all names
        for (String eachName : allNames) {
            System.out.println(eachName);

        }


    }

}
