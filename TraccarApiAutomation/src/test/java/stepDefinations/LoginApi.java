package stepDefinations;

import java.io.IOException;

import ApiResources.ApiResources;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import Resources.Payloads;
import Resources.Utils;
import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LoginApi extends Utils {
	RequestSpecification reqres;
	Response response;
	JsonPath js;
	
	@Given("user sets header")
	public void user_sets_header() throws IOException {
		
	       reqres =given().spec(requestSpecification()).body(Payloads.loginBody());
	 
	}

	@When("user sending {string} with {string} request")
	public void user_sending_with_request(String resourceApi, String method) throws IOException {

	ApiResources apiresource=ApiResources.valueOf(resourceApi);
		System.out.println("api endpoint is "+apiresource.getResource());
	
	 response = reqres.when().post(apiresource.getResource()).then().extract().response();
	 
		System.out.println("The response is "+response.asString());
	}

	@Then("status code in responsebody  is {int}")
	public void status_code_in_responsebody_is(int statuscode) {
	
		assertEquals(response.getStatusCode(), statuscode);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		assertEquals(getJsonPathData(response, key), value);
	}



}
