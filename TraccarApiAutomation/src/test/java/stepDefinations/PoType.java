package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.runner.Request;

import ApiResources.ApiResources;
import Resources.Payloads;
import Resources.Utils;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PoType extends Utils {
	Response response;
	String token;
	ApiResources apiresources;
	RequestSpecification request;

	@Given("user  should get {string} token from {string} set token to header")
	public void user_should_get_token_from_set_token_to_header(String key, String resource) throws IOException {

		request = loadingHeader(token);
	}

	@When("user should sents {string} request for {string}")
	public void user_should_sents_request_for(String method, String resource) throws IOException {

		apiresources = ApiResources.valueOf(resource);
		response = request.when()
				.get(apiresources.getResource()).then().extract().response();
	}

	@Then("the statuscode is {int}")
	public void the_statuscode_is(int code) {
		assertEquals(code, response.getStatusCode());
	}

	@Then("get all types")
	public void get_all_types() {

		int poSize = getJsonPathSize(response, "data.poTypes.size()");

		for (int i = 0; i < poSize; i++) {
			String type = getJsonPathData(response, "data.poTypes[" + i + "].type");
			System.out.println("The type of id:" + i + " is " + type);
		}

	}

}
