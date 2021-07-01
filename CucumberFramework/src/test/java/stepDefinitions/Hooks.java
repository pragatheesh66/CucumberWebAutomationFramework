package stepDefinitions;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.BasePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends BasePage{
	
	private Logger log = Logger.getLogger(Hooks.class);


	@Before
	public void beforevaldiation()
	{
		log.info("Launching browser and user is on testScripdemo homepage");
	}

	@After
	public void Aftervaldiation(Scenario scenario) {

		BasePage.captureScreenShot();
		
		log.info(scenario.isFailed());
   	 	if (scenario.isFailed()) {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotBytes, "image/png", "screenshot");
         
        }
   	 	
   	 	log.info(scenario.getName()+ "is completed");
		BasePage.quitDriver();
	}

}
