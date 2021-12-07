package ru.stqa.maven;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestAsFront extends TestBasis{
    @Before
    public void start(){

        driver.get("http://localhost/litecart/");
    }

    /*
    Переходим по ссылке, заполняем необходимые поля*/

    //@Test
    public void test11(){
        //клик по ссылке
        driver.findElement(By.cssSelector(".content table a")).click();
        //заполнение формы
        driver.findElement(By.cssSelector("[name=firstname]")).sendKeys("Chuck");
        driver.findElement(By.cssSelector("[name=lastname]")).sendKeys("Norris");
        driver.findElement(By.cssSelector("[name=address1]")).sendKeys("Oklahoma");
        driver.findElement(By.cssSelector("[name=postcode]")).sendKeys("64638");
        driver.findElement(By.cssSelector("[name=city]")).sendKeys("Ryan");
        //WebElement countrySelect= driver.findElement(By.cssSelector("[name=country_code]"));
        driver.findElement(By.cssSelector(".select2-selection")).click();
        driver.findElement(By.cssSelector("input.select2-search__field")).sendKeys("United States" + Keys.ENTER);
        //new Select(countrySelect).selectByVisibleText("United States");
        WebElement zoneSelect=driver.findElement(By.cssSelector("select[name=zone_code]"));
        wait.until(ExpectedConditions.visibilityOf(zoneSelect));
        new Select(zoneSelect).selectByVisibleText("Texas");
        String email= getSaltString();
        driver.findElement(By.cssSelector("[name=email]")).sendKeys(email+"@gmail.com");
        WebElement phoneField=driver.findElement(By.name("phone"));
        phoneField.click();
        phoneField.sendKeys(Keys.HOME);
        phoneField.sendKeys("123456789");
        driver.findElement(By.name("password")).sendKeys("user");
        driver.findElement(By.name("confirmed_password")).sendKeys("user");
        //клик по кнопке Создать аккаунт
        driver.findElement(By.name("create_account")).click();
        //logout
        driver.findElement(By.xpath("//a[.='Logout']")).click();
        //заполнение полей для авторизации
        driver.findElement(By.name("email")).sendKeys(email+"@gmail.com");
        driver.findElement(By.name("password")).sendKeys("user");
        //login
        driver.findElement(By.name("login")).click();
        //logout
        driver.findElement(By.xpath("//a[.='Logout']")).click();
    }

}