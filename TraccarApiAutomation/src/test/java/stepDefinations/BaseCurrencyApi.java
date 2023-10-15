package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import ApiResources.ApiResources;
import Resources.Payloads;
import Resources.Utils;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseCurrencyApi extends Utils {
	Response response;
	String token;
	ApiResources apiresources;
	RequestSpecification request;
	
	@Given("user should get {string} token from {string} set token to header")
	public void user_should_get_token_from_set_token_to_header(String key, String resource) throws IOException {
		token = loginAuthentication();

		 request = loadingHeader(token);
	}

	@When("user sents {string} request for {string}")
	public void user_sents_request_for(String method, String resource) throws IOException {
		apiresources= ApiResources.valueOf(resource);
	    response= request.when().get(apiresources.getResource()).then().extract().response();
	}

	@Then("statucode is {int}")
	public void statucode_is(int actualCode) {
		System.out.println(response.statusCode());
		assertingStatusCode(response,actualCode);
	}
}
