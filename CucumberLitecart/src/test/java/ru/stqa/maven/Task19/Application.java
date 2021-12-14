package Task19;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Properties;
public class Application {
    /*public WebDriver driver;
    public Properties prop;
    public WebDriverWait wait;

    private final HomePage home;
    private final ProductPage product;
    private final CartPage cart;

    public Application() throws IOException {
        prop = new Properties();
        prop.load(TestBase.class.getResourceAsStream("/testData.properties"));

        if ("chrome".equals(prop.getProperty("browser"))) {
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("start-fullscreen");
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, 10);
        } else if ("firefox".equals(prop.getProperty("browser"))) {
            FirefoxOptions options = new FirefoxOptions();
            //options.addArguments("start-fullscreen");
            driver = new FirefoxDriver(options);
            wait = new WebDriverWait(driver, 10);
        } else if ("edge".equals(prop.getProperty("browser"))) {
            EdgeOptions options = new EdgeOptions();
            options.addCommandSwitches("start-fullscreen");
            driver = new EdgeDriver(options);
            wait = new WebDriverWait(driver, 10);
        }

        home = new MainPage(driver);
        product = new ProductPage(driver);
        cart = new CartPage(driver);
    }
    public void quit() {
        driver.quit();
    }
    public void openHomePage() throws IOException {
        home.openHome();
    }

    public void productSelection() {
        home.addProductSelection.get(0).click();
    }

    public void addProductToCart(int iteration, String size) {
        product.addProduct(size);
        product.waitingForAnItem(iteration);
        product.back();
    }

    public void removingProductFromTheCart() {
        cart.openCart.click();
        int iteration = cart.addButtons.size();
        for (int i = 0; i < iteration; i++) {
            cart.getItem();
            cart.remove().click();
            cart.checkingForMissingElement();
        }
        cart.checkCart();
    }*/
}
