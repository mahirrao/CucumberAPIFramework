package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIUtilities
{
	public static RequestSpecification req;

	public RequestSpecification requestSpecifications() throws IOException
	{
		if (req == null)
		{
			PrintStream log = new PrintStream(new FileOutputStream("ProjectExecution.log"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalValues("baseURI")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log)). // Log request details
					addFilter(ResponseLoggingFilter.logResponseTo(log)). // Log response details
					setContentType(ContentType.JSON).build();
		}
		return req;
	}

	public static String getGlobalValues(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src\\test\\resources\\properties\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public static String getJsonKeyValues(Response response, String parameter)
	{
		String respon = response.asString();
//		System.out.println(response.asPrettyString());
		JsonPath js = new JsonPath(respon);
		return js.get(parameter).toString();
	}
}
