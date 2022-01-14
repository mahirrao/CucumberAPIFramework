package cucumber.Options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/java/features",
		glue = "stepDefinitions",
		monochrome = true,
//		tags = "@AddPlace or @DeletePlace",
		plugin = {
				"html:target/cucumber-html-report/cucumber.html",
				"json:target/cucumber-reports/cucumber.json",
				"junit:target/cucumber-reports/cucumber.xml",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }
		)
public class TestRunner extends AbstractTestNGCucumberTests
{

}
