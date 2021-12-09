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

public class Test13 extends TestBasis{
    //@Before
    public void openHomePage(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    //@Test
    public void test13() {
        List<String> locators = new ArrayList<>();
        locators.add("//span[@class='quantity' and contains(.,'1')]");
        locators.add("//span[@class='quantity' and contains(.,'2')]");
        locators.add("//span[@class='quantity' and contains(.,'3')]");
        for (String locator : locators) {
            //клик по первому товару
            driver.findElement(By.cssSelector("#box-most-popular li:first-child")).click();
            //добавление в корзину
            if (isElementPresent(By.cssSelector(".options"))) {
                WebElement select = driver.findElement(By.tagName("select"));
                new Select(select).selectByIndex(1);
                driver.findElement(By.name("add_cart_product")).click();
            } else {
                driver.findElement(By.name("add_cart_product")).click();
            }
            //ждем изменения количества
            wait.until((WebDriver d) -> d.findElement(By.xpath(locator)));
            //домой
            driver.findElement(By.className("fa-home")).click();
        }
        //заходим в корзину
        driver.findElement(By.cssSelector("#cart .link")).click();
        //цикл, пока есть товар в корзине
        while (isElementPresent(By.name("remove_cart_item"))) {
            //находим таблицу
            WebElement dataTable = driver.findElement(By.className("dataTable"));
            //ожидаем видимость кнопки remove
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("remove_cart_item")));
            //клик по remove
            driver.findElement(By.name("remove_cart_item")).click();
            //ждем обновление таблицы
            wait.until(ExpectedConditions.stalenessOf(dataTable));
        }
    }
}
