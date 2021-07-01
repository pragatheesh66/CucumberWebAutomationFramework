package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.java.en.Then;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import pages.CartPage;
import pages.HomePage;
import pages.WishListPage;

@RunWith(Cucumber.class)
public class StepsToVerifyItemsInWishlist{
	
	private Logger log = Logger.getLogger(StepsToVerifyItemsInWishlist.class);
	
	HomePage homePage = new HomePage();
	WishListPage wishlistpage = new WishListPage();
	CartPage cartpage = new CartPage();

	@Given("^I add \"([^\"]*)\" different products to my wishlist$")
    public void addDifferentProductsToMyWishlist(String noOfItems) {
    	
    	homePage.addItemsToWishList(Integer.parseInt(noOfItems));
    	log.info(noOfItems + "different items are added to the wishlist");
    	
    }

    @When("^I view my wishlist table$")
    public void viewMyWishlistTable() {
    	
    	homePage.navigateToWishlistPage();
    	log.info("Navigated to wishlist page");
    }
    
    @Then("^I find total \"([^\"]*)\" selected items in my wishlist$")
    public void validateTotalNumberOfItemsAdded(String noOfItems) throws Throwable {
    	
    	log.info("Validating number of items in the wish list");
    	wishlistpage.validateTotalItemsInWishList(Integer.parseInt(noOfItems));
    }


    @When("^I search for lowest price product$")
    public void searchForLowestPriceProduct() {
    	
    	log.info("searching for the lowest items in the cart");
    	wishlistpage.searchForLowestPricedItem();
    }
    

    @And("^I am able to add lowest price item to cart$")
    public void addLowestItemToCart() {
    	
    	log.info("Adding lowest priced item to cart");
    	wishlistpage.addLowestPricedItemToCart();
    }
   
    @Then("^I am able to verify the item in my cart$")
    public void verifyTheLowestItemInCart() {
    	
    	log.info("verfying the item in the cart");
    	wishlistpage.navigateToCartPage();
    	cartpage.verifyTheItemInCart();
    	
    }


}