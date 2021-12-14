package ru.stqa.maven;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Test13 extends TestBasis{

    //@Test
    public void test13() throws Exception{
        driver.get("http://localhost/litecart/");
        int numberOfProducts = 3;
        for (int a = 0; a < numberOfProducts; a++) {
            List<WebElement> productsMP = driver.findElements(By.cssSelector("#box-most-popular li"));
            productsMP.get(0).click();
            WebElement quantityInCart = driver.findElement(By.cssSelector("#cart .quantity"));
            String quantity = quantityInCart.getAttribute("textContent");
            int intQ = Integer.valueOf(quantity);
            if(isElementPresent(By.cssSelector("[name='options[Size]']"))){
                driver.findElement(By.cssSelector("[name='options[Size]']")).click();
                driver.findElement(By.cssSelector("[name='options[Size]'] [value=Medium]")).click();
            }
            driver.findElement(By.cssSelector("button[value='Add To Cart']")).click();
            wait.until(ExpectedConditions.attributeContains(quantityInCart, "textContent", Integer.toString(intQ + 1)));
            if(a==numberOfProducts-1){
                driver.findElement(By.cssSelector("a.link[href*=checkout]")).click();
            }else{
                driver.navigate().back();
            }
        }
        wait.until(titleIs("Checkout | My Store"));
        WebElement CheckoutSummary = driver.findElement(By.cssSelector("#box-checkout-summary"));
        List <WebElement> CheckoutSummaryProducts = CheckoutSummary.findElements(By.cssSelector("td.item"));
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
        System.out.println("DONE!!!");
    }
}
