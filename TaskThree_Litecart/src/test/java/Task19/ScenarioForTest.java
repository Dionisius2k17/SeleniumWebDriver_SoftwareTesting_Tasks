package Task19;

import org.junit.Test;

public class ScenarioForTest {
    public Application app = new Application();

    @Test
    public void addingAndDeletingProducts() throws Exception {
        int amountOfProductsToAdd = 3;
        while (amountOfProductsToAdd>0) {
            app.chooseProductByParameters("Most Popular");
            app.addProductToCart(1,"Large");
            amountOfProductsToAdd--;
        }
        app.removeAllProductsFromTheCart();
        app.closeApplication();
    }
}
