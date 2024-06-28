package tests;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeviceApiTest {

	 private static final String BASE_URL = "https://api.restful-api.dev/objects";
	    private static final String DEVICE_NAME = "Apple Max Pro 1TB";
	    private static final int DEVICE_YEAR = 2023;
	    private static final double DEVICE_PRICE = 7999.99;
	    private static final String DEVICE_CPU_MODEL = "Apple ARM A7";
	    private static final String DEVICE_HARD_DISK_SIZE = "1 TB";

	    @Test
	    public void addDeviceTest() throws IOException {
	        // Request payload
	        Map<String, Object> data = new HashMap<>();
	        data.put("year", DEVICE_YEAR);
	        data.put("price", DEVICE_PRICE);
	        data.put("CPU model", DEVICE_CPU_MODEL);
	        data.put("Hard disk size", DEVICE_HARD_DISK_SIZE);

	        Map<String, Object> payload = new HashMap<>();
	        payload.put("name", DEVICE_NAME);
	        payload.put("data", data);

	        // Send POST request to add new device
	        RequestSpecification request = RestAssured.given();
	        request.header("Content-Type", "application/json");
	        request.body(payload);

	        Response response = request.post(BASE_URL);

	        // Validate response status code
	        Assert.assertEquals(response.getStatusCode(), 200);
	        // Extract response fields
	        String deviceId = response.jsonPath().getString("id");
	        String name = response.jsonPath().getString("name");
	        String createdAt = response.jsonPath().getString("createdAt");
	        int year = response.jsonPath().getInt("data.year");
	        double price = response.jsonPath().getDouble("data.price");
	        String cpuModel = response.jsonPath().getString("data.'CPU model'");
	        String hardDiskSize = response.jsonPath().getString("data.'Hard disk size'");

	     // Validation checks
	        Map<String, Boolean> validations = new HashMap<>();
	        validations.put("ID is not null", deviceId != null);
	        validations.put("Name matches", DEVICE_NAME.equals(name));
	        validations.put("CreatedAt is not null", createdAt != null);
	        validations.put("Year matches", DEVICE_YEAR == year);
	        validations.put("Price matches", DEVICE_PRICE == price);
	        validations.put("CPU model matches", DEVICE_CPU_MODEL.equals(cpuModel));
	       validations.put("Hard disk size matches", DEVICE_HARD_DISK_SIZE.equals(hardDiskSize));
	        // Generate report
	        StringBuilder report = new StringBuilder("Validation Report:\n");
	        for (Map.Entry<String, Boolean> validation : validations.entrySet()) {
	            String status = validation.getValue() ? "Pass" : "Fail";
	            report.append(validation.getKey()).append(": ").append(status).append("\n");
	        }

	        System.out.println(report.toString());

	        // Save report to a file
	        try (FileWriter reportFile = new FileWriter("validation_report.txt")) {
	            reportFile.write(report.toString());
	        }
    }
}