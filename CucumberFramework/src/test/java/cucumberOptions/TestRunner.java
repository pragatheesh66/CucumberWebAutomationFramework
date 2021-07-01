package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/features",
	    glue="stepDefinitions",
	    tags="@AddToWishListPassed",
	    plugin= {"pretty","html:CucumberTestReport.html", "html:target/cucumber-html-report/cucumber.html","junit:target/cucumber-reports/cucumber.xml"},
	    
	    monochrome=true
	)

public class TestRunner{
	

}
