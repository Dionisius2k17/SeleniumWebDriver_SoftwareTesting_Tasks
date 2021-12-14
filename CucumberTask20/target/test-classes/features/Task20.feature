Feature: Adding and removing products

  Scenario:
    Given '3' is amount of different products we want to add
    And category of product box on main page - 'Campaigns'
    And '1' is the quantity and 'Medium' is the size of each product we are adding
    When we choose and click a product on main page
    Then product page opens
    When we choose quantity and size (if it is possible) of a product and click on AddToCart button on product page
    Then product(s) are added to the cart
    When we add all the products we want to the cart
    Then cart page opens
    When we remove all products from the cart one by one
    Then the cart will be empty