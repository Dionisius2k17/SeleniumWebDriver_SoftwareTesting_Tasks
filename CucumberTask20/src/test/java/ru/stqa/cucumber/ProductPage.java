package ru.stqa.cucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static ru.stqa.cucumber.Utils.isElementPresent;
import static org.junit.Assert.assertTrue;

public class ProductPage extends Page {

    public static int quantityToAdd;

    public ProductPage(WebDriver driver) {
        super(driver);
    }


    public static boolean productPageOpened(){
        try {
            assertTrue(isElementPresent(By.cssSelector("#box-product")));
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public static void chooseQuantity(int quantity) {
        quantityToAdd = quantity;
        new Actions(driver)
                .moveToElement(driver.findElement(By.cssSelector("[name=quantity]"))).click()
                .keyDown(Keys.CONTROL).sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .sendKeys(Integer.toString(quantityToAdd))
                .perform();
    }

    public static void chooseSize(String sizeIfPossible) {
        if(isElementPresent(By.cssSelector("[name='options[Size]']"))){
            driver.findElement(By.cssSelector("[name='options[Size]']")).click();
            driver.findElement(By.cssSelector("[name='options[Size]'] [value="+sizeIfPossible+"]")).click();
        }
    }

    public static void pressAddtoCart() {
        Cart.getCurrentAmount();
        driver.findElement(By.cssSelector("button[value='Add To Cart']")).click();
        assertTrue(Cart.confirmAddingAProducts());
    }
}
