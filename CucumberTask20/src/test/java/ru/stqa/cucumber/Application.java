package ru.stqa.cucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

    public void chooseProductByParametersMP(String ProductCategory) throws Exception {
        MainPage.open();
        MainPage.chooseProductCategory(ProductCategory);
        MainPage.chooseTheFirstProduct();
    }

    public void addProductToCart(int Quantity, String SizeIfPossible){
        ProductPage.chooseQuantity(Quantity);
        ProductPage.chooseSize(SizeIfPossible);
        ProductPage.pressAddtoCart();
    }

    public void removeAllProductsFromTheCart(){
        CartPage.removeAllProducts();
    }

    public void closeApplication(){
        driver.quit();
        driver = null;
    }
}
