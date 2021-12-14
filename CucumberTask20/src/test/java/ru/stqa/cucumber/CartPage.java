package ru.stqa.cucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static ru.stqa.cucumber.Utils.isElementPresent;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class CartPage extends Page {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public static void open() {
        driver.findElement(By.cssSelector("a.link[href*=checkout]")).click();
    }

    public static boolean cartPageOpened(){
        try {
            wait.until(titleIs("Checkout | My Store"));
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public static void removeAllProducts() {
        WebElement CheckoutSummary = driver.findElement(By.cssSelector("#box-checkout-summary"));
        List<WebElement> CheckoutSummaryProducts = CheckoutSummary.findElements(By.cssSelector("td.item"));
        int typesOfProductsInCart = CheckoutSummaryProducts.size();
        while (true){
            if(isElementPresent(By.cssSelector("button[value=Remove]"))){
                WebElement removeButton = driver.findElement(By.cssSelector("button[value=Remove]"));
                wait.until(visibilityOf(removeButton));
                removeButton.click();
                wait.until(numberOfElementsToBe(By.cssSelector("td.item"), typesOfProductsInCart-1));
                typesOfProductsInCart--;
                continue;
            }
            else{
                wait.until(ExpectedConditions.stalenessOf(CheckoutSummary));
                break;
            }
        }
    }

    public static boolean cartIsEmpty(){
        try {
            driver.findElement(By.cssSelector("#checkout-cart-wrapper em")).getText().equals("There are no items in your cart.");
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
