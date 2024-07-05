package tests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

public class gettest {
	@Test(priority =0)
	public void getUsersPage2() {
        // Set the base URL
        RestAssured.baseURI = "https://reqres.in";

        // Perform the GET request and get the response
        Response response = given()
                .when()
                .get("/api/users?page=2")
                .then()
                .statusCode(200) // Verify that the status code is 200
                .body("page", equalTo(2)) // Verify that the page number is 2
                .body("data", notNullValue()) // Verify that the data field is not null
                .extract()
                .response();

        // Print the response body
        System.out.println(response.asString());
    }
	
	 @Test(priority =1)
	    public void getUser23() {
	        // Set the base URL
	        RestAssured.baseURI = "https://reqres.in";

	        // Perform the GET request and get the response
	        Response response = given()
	                .when()
	                .get("/api/users/23")
	                .then()
	                .statusCode(404) // Verify that the status code is 404
	                .extract()
	                .response();

	        // Print the response body
	        System.out.println(response.asString());
	    }

}
