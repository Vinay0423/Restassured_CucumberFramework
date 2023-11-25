package stepDefinations;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import ApiResources.ApiResources;
import Resources.Payloads;
import Resources.Utils;


public class CurrentApi extends Utils{
	Response response;
	String token;
	ApiResources apiresources;
	RequestSpecification request;
	
	@Given("get {string} token from {string} set token to header")
	public void get_token_from_set_token_to_header(String key, String resource) throws IOException {

		request = loadingHeader();
	}

	@When("sending {string} with {string} request")
	public void sending_with_request(String resource, String method) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		
		
	 response= LoadingResourceWithRequestMethods(request, method, resource)
			 .then().extract().response();
	   System.out.println(response.getBody().asPrettyString());
	}

	@Then("statusCode is {int}")
	public void status_code_is(int actualStatuscode) {
		assertingStatusCode(response,actualStatuscode);
	}
	


	@Then("{string} value  is {string}")
	public void value_is(String key, String value) {
		assertingStringValues(response,key,value);
	}

	@Then("{string}  the value for {string}")
	public void the_value_for(String value, String key) {
		assertingStringValues(response,key,value);
	}

}
