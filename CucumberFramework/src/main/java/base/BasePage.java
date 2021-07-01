package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

public class BasePage {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Logger log = Logger.getLogger(BasePage.class);
	public static FileInputStream fis;
	private String testProjectLocation = System.getProperty("user.dir");
	private String mainFolder = testProjectLocation + "\\src\\main\\java";
	private String configFile = mainFolder + "\\resources\\config.properties";

	public BasePage()  {

		if(driver==null) {
			try {
				fis = new FileInputStream(configFile);
				config.load(fis);
			} 
			catch (IOException e) {
				log.error("configuration.properties not found at " + configFile);
	            throw new RuntimeException("configuration.properties not found at " + configFile);
			}
	
			//***************************Initializing driver******************************************
			
			String browser = config.getProperty("browser");
	        switch (browser) {
		            case "Chrome":
		                System.setProperty("webdriver.chrome.driver", mainFolder + config.getProperty("chrome.driver.path"));
		                driver = new ChromeDriver();
		                break;
		            case "Firefox":
		                System.setProperty("webdriver.gecko.driver",mainFolder + config.getProperty("ie.driver.path"));
		                driver = new FirefoxDriver();
		                break;
		            case "InternetExplorer":
		                System.setProperty("webdriver.ie.driver",mainFolder + config.getProperty("gecko.driver.path"));
		                driver = new InternetExplorerDriver();
		                break;
		            default:
		                throw new RuntimeException("Unsupported Browser Type - " + browser);
		        }
	        
        	driver.get(config.getProperty("applicationURL"));
        	log.info("Application" + config.getProperty("applicationURL") + "successfully");
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	        log.info(config.getProperty("browser") + " Browser is launched");
	
		}
	}

	public static void quitDriver() {
	
		driver.quit();
	}
	
	//********************************* Common functions **********************
	
	public static void clickElement(By locator) {
	
		driver.findElement(locator).click();
		log.info(locator.toString()+" - clicked");
	}
	
	public static void enterText(By locator, String value) {
	
		driver.findElement(locator).sendKeys(value);
		log.info(value+" - is entered");
	}
	
	public static void selectDropdown(By locator, String value) {
	
		WebElement dropdown = driver.findElement(locator);
	
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		log.info(value+" - is selected from dropdown");
	
	}
	
	public static void captureScreenShot() {
	
		File scr =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddyyyymmss");
		String filename = simpleDateFormat.format(date);
	
		try {
			FileUtils.copyFile(scr, new File(System.getProperty("user.dir")+"//screenshots//" + filename +".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}
}
