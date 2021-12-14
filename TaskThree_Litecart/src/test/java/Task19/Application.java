package Task19;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Properties;

public class Application {
    private WebDriver driver;

    private MainPage mainPage;
    private ProductPage productPage;
    private CartPage cartPage;

    public Application() {

        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);

    }

    public void chooseProductByParameters(String ProductCategory) throws Exception {
        MainPage.open();
        MainPage.chooseProductCategory(ProductCategory);
        MainPage.chooseTheFirstProduct();
    }

    public void addProductToCart(int Quantity, String SizeIfPossible){
        ProductPage.chooseQuantity(Quantity);
        ProductPage.chooseSize(SizeIfPossible);
        ProductPage.pressAddtoCart();
        System.out.println("Product added to the cart\n");
    }

    public void removeAllProductsFromTheCart(){
        CartPage.open();
        CartPage.removeAllProducts();
        System.out.println("All products were removed from the cart\n");
    }

    public void closeApplication(){
        driver.quit();
        System.out.println("The Application is closed\n");
        driver = null;
    }
}
