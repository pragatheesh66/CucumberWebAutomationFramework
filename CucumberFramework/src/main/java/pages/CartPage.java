package pages;

import org.openqa.selenium.By;
import org.testng.log4testng.Logger;

import base.BasePage;

public class CartPage extends BasePage{
	
	private static Logger log = Logger.getLogger(BasePage.class);
	
	//Locators
	
	public static final By trCartlistItems = By.xpath("//tbody//tr[contains(@class,'cart_item')]");
	public static final By spnProductPrice = By.xpath("//td[@class='product-price']//bdi");
	public static final By lblProductName = By.xpath("//td[@class='product-name']/a");
	
	
	/**
	 * Verify the item details in cart
	 */
	public void verifyTheItemInCart() {
			
		Double cost = Double.parseDouble(driver.findElement(spnProductPrice).getText().replace("£", ""));
				
		if(Integer.parseInt(WishListPage.minimumvalue) == (int) Math.round(cost)) {
			log.info("Lowest priced item is added to cart as expected");
		}else {
			log.error("Lowest priced item is not added to cart as expected");			
		}
		
		System.out.println(driver.findElement(lblProductName).getText());
		log.info(driver.findElement(lblProductName).getText());
	}

}
