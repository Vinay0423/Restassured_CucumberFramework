package stepDefinations;

import java.io.IOException;

import ApiResources.ApiResources;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import Resources.Utils;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetUsers extends Utils {

	String token;
	Response response;
	RequestSpecification request;

	@Given("setting headers")
	public void setting_headers() throws IOException {

		request = loadingHeader(token);
	}

	@When("user send the get request using {string}")
	public void user_send_the_get_request_using(String resource) throws IOException {

		ApiResources apiresource = ApiResources.valueOf(resource);

		response = request.when().get(apiresource.getResource()).then().extract().response();

	}

	@Then("statuscode of response is {int}")
	public void statuscode_of_response_is(int code) {
		assertingStatusCode(response,code);
	}

	@Then("getting the response body")
	public void getting_the_response_body() {
		// System.out.println( response.asPrettyString()); //print response in pretty
		// format
		System.out.println(response.asString());
	}
}
