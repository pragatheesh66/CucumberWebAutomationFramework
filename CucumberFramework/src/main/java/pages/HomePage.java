package pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class HomePage extends BasePage {
	
	private Logger log = Logger.getLogger(HomePage.class);

	//Locators
	public static final By lnkaddToWishList = By.xpath("//a[@data-title='Add to wishlist']");
	public static final By lnkWishList = By.xpath("(//a[@title='Wishlist'])[1]");

	
	
	/**
	 * Add given number of items to wish list
	 * @param noOfItems
	 */

	public void addItemsToWishList(int noOfItems) {

		List<WebElement> items = driver.findElements(lnkaddToWishList);
		
		for(int i=0;i<=noOfItems-1; i++) {
				items.get(i).click();
		}
		
		log.info(noOfItems + " items added to the cart");

	}
	
	/**
	 * Navigate to wish list page
	 * @return
	 */
	public void navigateToWishlistPage() {
		
		clickElement(lnkWishList);
		
	}
}
