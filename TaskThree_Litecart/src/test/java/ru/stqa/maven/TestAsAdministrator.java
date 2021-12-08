package ru.stqa.maven;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class TestAsAdministrator extends TestBasis{

        @Before
        public void openHomePage(){
            driver.get("http://localhost/litecart/admin/");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.name("login")).click();
        }

        //@Test
        public void testOne() {
            WebElement menu = driver.findElement(By.id("box-apps-menu-wrapper"));
            List<WebElement> strings = menu.findElements(By.className("name"));
            for (int i = 0; i < strings.size(); i++) {
                List<WebElement> s = driver.findElements(By.cssSelector("ul#box-apps-menu>li"));
                s.get(i).click();
                assertTrue(isElementPresent(By.tagName("h1")));
                List<WebElement> substrings = driver.findElements(By.cssSelector("li.selected>ul>li"));
                for (int n = 0; n < substrings.size(); n++) {
                    List<WebElement> ss = driver.findElements(By.cssSelector("li.selected>ul>li"));
                    ss.get(n).click();
                    assertTrue(isElementPresent(By.tagName("h1")));
                }
            }
        }

    //@Test
    public void test12(){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //проверяем количество позиций создаваемого товара в каталоге
        driver.findElement(By.cssSelector("li#app-:nth-of-type(2)")).click();
        driver.findElement(By.cssSelector("#doc-catalog")).click();
        driver.findElement(By.xpath("//a[.='Rubber Ducks']")).click();
        List<WebElement> defaultDucks = driver.findElements(By.xpath("//a[.='Globus Duck']"));
        //переход на создание товара
        driver.findElement(By.cssSelector("[href*=edit_product]")).click();

        //Блок General
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("date_valid_to")));
        driver.findElement(By.xpath("//label[.=' Enabled']")).click();
        driver.findElement(By.name("name[en]")).sendKeys("Globus Duck");
        driver.findElement(By.name("code")).sendKeys("DD001");
        driver.findElement(By.xpath("//input[@data-name='Root']")).click();
        driver.findElement(By.xpath("//input[@data-name='Rubber Ducks']")).click();
        driver.findElement(By.xpath("//input[contains(@name,'product_groups[]') and contains (@value,'1-3')]")).click();
        driver.findElement(By.name("quantity")).sendKeys("3");
        String link= "GlobusDuck.jpg";
        String path = Path.of(link).toAbsolutePath().toString();
        driver.findElement(By.name("new_images[]")).sendKeys(path);
        driver.findElement(By.name("date_valid_from")).sendKeys("20112021");
        driver.findElement(By.name("date_valid_to")).sendKeys("20112022");

        //Блок Information
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("meta_description[en]")));
        driver.findElement(By.xpath("//a[.='Information']")).click();
        WebElement manufacturer = driver.findElement(By.name("manufacturer_id"));
        new Select(manufacturer).selectByValue("1");
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys("Just a little rubber duck with sea and continents. ");
        driver.findElement(By.name("head_title[en]")).sendKeys("Globus Duck");

        //Блок Prices
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("table-campaigns")));
        driver.findElement(By.xpath("//a[.='Prices']")).click();
        driver.findElement(By.name("purchase_price")).clear();
        driver.findElement(By.name("purchase_price")).sendKeys("20");
        WebElement currency= driver.findElement(By.name("purchase_price_currency_code"));
        new Select(currency).selectByValue("USD");
        driver.findElement(By.name("prices[USD]")).sendKeys("20");
        driver.findElement(By.name("prices[EUR]")).sendKeys("25");

        //сохраняем и проверяем
        driver.findElement(By.name("save")).click();
        driver.findElement(By.cssSelector("#doc-catalog")).click();
        driver.findElement(By.xpath("//a[.='Rubber Ducks']")).click();
        List<WebElement> createdDucks = driver.findElements(By.xpath("//a[.='Globus Duck']"));
        assert createdDucks.size()>defaultDucks.size();
    }

    //@Test
    public void test13(){
        List<String> locators = new ArrayList<>();
        locators.add("//span[@class='quantity' and contains(.,'1')]");
        locators.add("//span[@class='quantity' and contains(.,'2')]");
        locators.add("//span[@class='quantity' and contains(.,'3')]");
        for (String locator:locators) {
            //клик по первому товару
            driver.findElement(By.cssSelector("#box-most-popular li:first-child")).click();
            //добавление в корзину
            if (isElementPresent(By.cssSelector(".options"))){
                WebElement select = driver.findElement(By.tagName("select"));
                new Select(select).selectByIndex(1);
                driver.findElement(By.name("add_cart_product")).click();}
            else {driver.findElement(By.name("add_cart_product")).click();}
            //ждем изменения количества
            wait.until((WebDriver d) -> d.findElement(By.xpath(locator)));
            //домой
            driver.findElement(By.className("fa-home")).click();
        }
        //заходим в корзину
        driver.findElement(By.cssSelector("#cart .link")).click();
        //цикл, пока есть товар в корзине
        while (isElementPresent(By.name("remove_cart_item"))){
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



