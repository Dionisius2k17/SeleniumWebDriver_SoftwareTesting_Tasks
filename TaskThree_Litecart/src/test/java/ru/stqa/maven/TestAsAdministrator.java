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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("date_valid_to")));
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("meta_description[en]")));
        driver.findElement(By.xpath("//a[.='Information']")).click();
        WebElement manufacturer = driver.findElement(By.name("manufacturer_id"));
        new Select(manufacturer).selectByValue("1");
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys("Just a little rubber duck with sea and continents. ");
        driver.findElement(By.name("head_title[en]")).sendKeys("Globus Duck");

        //Блок Prices
        driver.findElement(By.xpath("//a[.='Prices']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("table-campaigns")));
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

    @Test
    public void test14() {
        driver.findElement(By.cssSelector("[href*=countries]")).click();
        driver.findElement(By.className("button")).click();
        //получаем id основного окна и создаем список ссылочных элементов
        String originalWindow= driver.getWindowHandle();
        List<WebElement> elements = new ArrayList<>();
        elements.add(driver.findElement(By.cssSelector("[href*=alpha-2] i")));
        elements.add(driver.findElement(By.cssSelector("[href*=alpha-3] i")));
        elements.add(driver.findElement(By.xpath("//strong[.='Tax ID Format']/..//i")));
        elements.add(driver.findElement(By.cssSelector("[href*=address-formats] i")));
        elements.add(driver.findElement(By.xpath("//strong[.='Postcode Format']/..//i")));
        elements.add(driver.findElement(By.cssSelector("[href*=currency] i")));
        elements.add(driver.findElement(By.cssSelector("[href*=calling_codes] i")));
        for (WebElement element:elements) {
            //переключаемся на основное окно
            driver.switchTo().window(originalWindow);
            //кликаем по ссылке, ожидаем появление окна
            element.click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            //получаем id окон и удаляем id основного окна из списка
            Set<String> handles = driver.getWindowHandles();
            handles.remove(originalWindow);
            //сохраняем id нового окна в переменную
            String newWindow = handles.iterator().next();
            //переключаемся на новое окно
            driver.switchTo().window(newWindow);
            //закрываем и переключаемся на основное окно
            driver.close();
            driver.switchTo().window(originalWindow);
        }
    }
}



