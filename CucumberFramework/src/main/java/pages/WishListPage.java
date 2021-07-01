package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class WishListPage extends BasePage {
	
	private Logger log = Logger.getLogger(WishListPage.class);
	
	public static String minimumvalue;
	
	//Locators
	
	public static final By trWishlistItems = By.xpath("//tbody[@class='wishlist-items-wrapper']//tr");
	public static final By spnProductPrice = By.xpath("//td[@class='product-price']//bdi");
	public static final By lnkCartPage = By.xpath("//a[@title='Cart']");
	
	/**
	 * Validate number of items in WishList
	 * @param expectedCount
	 */
	public void validateTotalItemsInWishList(int expectedCount) {
		
				
		int numberOfItems = driver.findElements(trWishlistItems).size();
		
		Assert.assertEquals(numberOfItems, expectedCount);
	}
	
	/**Add the item with lowest price to the cart
	 * 
	 */
	public void searchForLowestPricedItem() {
		
		List<Integer> allitems = new ArrayList<Integer>();
		
		for(WebElement wb: driver.findElements(spnProductPrice)) {
			Double cost = Double.parseDouble(wb.getText().replace("£", ""));
			allitems.add((int) Math.round(cost));
		}
		
		Collections.sort(allitems);
		
		minimumvalue = allitems.get(0).toString();
	}
	
	/**Add the item with lowest price to the cart
	 * 
	 */
	public void addLowestPricedItemToCart() {
		
		List<WebElement> minVal = driver.findElements(By.xpath("//td[@class='product-price']//bdi[text()="+minimumvalue+"]//ancestor::tr//td[6]/a"));
		
		minVal.get(0).click();
		
		log.info("Minimum value" + minVal.get(0) + "is added to the cart");
		
	}
	
	/**
	 * Navigate to cart page from Wish list
	 */
	public void navigateToCartPage() {
		
		clickElement(lnkCartPage);
		
	}
	
	
	
}
