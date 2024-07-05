package tests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
public class putrequest {
	@org.testng.annotations.Test
    public void updateUser() {
        // Set the base URL
        RestAssured.baseURI = "https://reqres.in";

        // Define the data to be sent in the PUT request
        String requestBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        // Perform the PUT request and get the response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/api/users/2")
                .then()
                .statusCode(200) // Verify that the status code is 200
                .body("name", equalTo("morpheus")) // Verify that the name is "morpheus"
                .body("job", equalTo("zion resident")) // Verify that the job is "zion resident"
                .body("updatedAt", notNullValue()) // Verify that the updatedAt field is not null
                .extract()
                .response();

        // Print the response body
        System.out.println(response.asString());
    }

}
