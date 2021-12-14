package ru.stqa.cucumber;

import io.cucumber.java8.En;

import static org.junit.Assert.assertTrue;

public class Test20 implements En{
    private Application app = new Application();

    private int AmountOfProductsToAdd;
    private String ProductCategory;
    private int QuantityOfSpecificProduct;
    private String SizeOfSpecificProduct;

    public Test20() {
        Given("{string} is amount of different products we want to add", (String amount) -> {
            AmountOfProductsToAdd = Integer.valueOf(amount);
        });
        And("category of product box on main page - {string}", (String category) -> {
            ProductCategory = category;
        });
        And("{string} is the quantity and {string} is the size of each product we are adding", (String quantity, String size) -> {
            QuantityOfSpecificProduct = Integer.valueOf(quantity);
            SizeOfSpecificProduct = size;
        });
        When("^we choose and click a product on main page$", () -> {
            app.chooseProductByParametersMP(ProductCategory);
        });
        Then("^product page opens$", () -> {
            assertTrue(ProductPage.productPageOpened());
        });
        When("^we choose quantity and size \\(if it is possible\\) of a product and click on AddToCart button on product page$", () -> {
            app.addProductToCart(QuantityOfSpecificProduct,SizeOfSpecificProduct);
        });
        Then("^product\\(s\\) are added to the cart$", () -> {
            assertTrue(Cart.confirmAddingAProducts());
            AmountOfProductsToAdd--;
        });
        When("^we add all the products we want to the cart$", () -> {
            while (AmountOfProductsToAdd>0){
                app.chooseProductByParametersMP(ProductCategory);
                app.addProductToCart(QuantityOfSpecificProduct,SizeOfSpecificProduct);
                AmountOfProductsToAdd--;
            }
            CartPage.open();
        });
        Then("^cart page opens$", () -> {
            assertTrue(CartPage.cartPageOpened());
        });
        When("^we remove all products from the cart one by one$", () -> {
            app.removeAllProductsFromTheCart();
        });
        Then("^the cart will be empty$", () -> {
            assertTrue(CartPage.cartIsEmpty());
            app.closeApplication();
        });


    }
}