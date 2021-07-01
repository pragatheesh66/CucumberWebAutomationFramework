Feature: Acceptance testing to verify the products are added to the cart from wishlist


@AddToWishListPassed
Scenario: Validate the products on cart
	Given I add "4" different products to my wishlist
	When I view my wishlist table
	Then I find total "4" selected items in my wishlist
	When I search for lowest price product
	And I am able to add lowest price item to cart
	Then I am able to verify the item in my cart
