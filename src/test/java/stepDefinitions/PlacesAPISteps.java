package stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import TestData.APIBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utilities.APIResouces;
import utilities.APIUtilities;

public class PlacesAPISteps extends APIUtilities
{

	ResponseSpecification resSpec;
	RequestSpecification res;
	RequestSpecification req;
	static Response response;
	static String place_id;
	APIBody data = new APIBody();

	@Given("Add Place Payload with {string}, {string}, {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException
	{
//		AddPlace addPlace = addPlacePayload();

		req = requestSpecifications();

		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		res = given().spec(req).body(data.addPlacePayload(name, language, address));
	}

	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException
	{
		req = requestSpecifications();

//		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		res = given().spec(req).body(data.deletePlacePayload(place_id));
	}

	@When("user Calls {string} with {string} http Request")
	public void user_calls_with_http_request(String resource, String request)
	{
		APIResouces resourceAPI = APIResouces.valueOf(resource);
		System.out.println(resourceAPI.getResource());

		switch (request) {
		case "POST":
			response = res.when().post(resourceAPI.getResource()); // .then().spec(resSpec).extract().response();
			break;
		case "PUT":
			response = res.when().put(resourceAPI.getResource()); // .then().spec(resSpec).extract().response();
			break;
		case "GET":
			response = res.when().get(resourceAPI.getResource()); // .then().spec(resSpec).extract().response();
			break;
		case "DELETE":
			response = res.when().delete(resourceAPI.getResource()); // .then().spec(resSpec).extract().response();
			break;
		}
	}

	@Then("the API call is {string} with Status Code {int}")
	public void the_api_call_is_with_status_code(String callStatus, int statusCode)
	{
		Assert.assertEquals(response.getStatusCode(), statusCode);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String parameter, String expectedValue)
	{
		Assert.assertEquals(getJsonKeyValues(response, parameter).toString(), expectedValue);
	}
	@Then("verify placeId created for map with {string} using {string}")
	public void verify_place_id_created_for_map_with_using(String name, String resource)
	{
		place_id = getJsonKeyValues(response, "place_id");
		res = given().spec(req).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonKeyValues(response, "name");
		Assert.assertEquals(actualName, name);
	}

}
