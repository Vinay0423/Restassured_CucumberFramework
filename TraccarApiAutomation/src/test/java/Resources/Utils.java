package Resources;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import ApiResources.ApiResources;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	RequestSpecification reqres;
	String token;

	public String getGlobalProperty(String value) throws IOException {
		Properties prop = new Properties();
		FileInputStream ips = new FileInputStream(
				"C:\\Users\\Vinay M\\eclipse-workspace\\TraccarApiAutomation\\src\\test\\java\\Resources\\global.properties");
		prop.load(ips);
		return prop.getProperty(value);
	}

	public RequestSpecification requestSpecification() throws IOException {

		if (reqres == null) {
			reqres = new RequestSpecBuilder().setBaseUri(getGlobalProperty("baseUrl"))
					.addHeader("User-Agent", "PostmanRuntime/7.32.3")
					.addHeader("Accept", "[{\"key\":\"Accept\",\"value\":\"application/json, text/plain, */*\"}]")
					.addHeader("Accept-Encoding", "gzip, deflate, br")
					.addHeader("Content-Type", "application/json;charset=UTF-8")
					.addHeader("Cookie",
							"_ga_P6QCGH5FEM=GS1.1.1690442940.3.0.1690442940.0.0.0; _ga=GA1.2.903091232.1690281876")
					.addHeader("Origin", "https://traccar-qa.spurtreetech.com")
					.addHeader("Referer", "https://traccar-qa.spurtreetech.com/login")
					.addHeader("Sec-Fetch-Dest", "empty").addHeader("Sec-Fetch-Mode", "cors")
					.addHeader("Sec-Fetch-Site", "same-origin")
					.addHeader("User-Agent",
							"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36")
					.addHeader("sec-ch-ua",
							"\"Google Chrome\";v=\"111\", \"Not(A:Brand\";v=\"8\", \"Chromium\";v=\"111\"")
					.addHeader("sec-ch-ua-platform", "\"Linux\"").addHeader("sec-ch-ua-mobile", "?0")
					.addHeader("Connection", "keep-alive").build();
			return reqres;
		}

		return reqres;

	}

	public String getJsonPathData(Response response, String key) {
		JsonPath js = new JsonPath(response.asString());
		return js.get(key).toString();

	}

	public int getJsonPathSize(Response response, String Key) {
		JsonPath js = new JsonPath(response.asString());
		return js.getInt(Key);
	}

	public String loginAuthentication() throws IOException {
		ApiResources apiresources = ApiResources.valueOf("loginApi");
		Response response = given().spec(requestSpecification()).body(Payloads.loginBody()).when()
				.post(apiresources.getResource()).then().extract().response();
		String token = getJsonPathData(response, "data.user.token");
		String bearer = "Bearer " + token;

		return bearer;
	}

	public void assertingStatusCode(Response response, int actualStatuscode) {
		assertEquals(actualStatuscode, response.getStatusCode());
	}

	public void assertingStringValues(Response response, String key, String expectedValue) {
		assertEquals(expectedValue, getJsonPathData(response, key));
	}

	public RequestSpecification loadingHeader(String bearertoken) throws IOException {
		if (token == null) {
			token = loginAuthentication();
		}
		RequestSpecification request = given().spec(requestSpecification()).header("Authorization", bearertoken);
		return request;
	}
}
