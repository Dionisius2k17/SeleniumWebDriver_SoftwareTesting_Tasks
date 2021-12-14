package ru.stqa.maven;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Test14 extends TestBasis{
    //@Before
    public void openHomePage(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    //@Test
    public void test14() {
        List<WebElement> list = driver.findElements(By.cssSelector("li#app-"));
        list.get(2).click();
        driver.findElement(By.cssSelector("td#content a.button")).click();
        String activeWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();
        List<WebElement> externalLinks = driver.findElements(By.cssSelector("td#content i.fa.fa-external-link"));
        int iterations = externalLinks.size();
        for (int i = 0; i < iterations; i++) {
            externalLinks.get(i).click();
            String newWindow = wait.until(new ExpectedCondition<String>() {
                @NullableDecl
                @Override
                public String apply(@NullableDecl WebDriver driver) {
                    Set<String> newWindows = driver.getWindowHandles();
                    newWindows.removeAll(oldWindows);
                    return newWindows.size() > 0 ? newWindows.iterator().next() : null;
                }
            });
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(activeWindow);
            Assert.assertEquals("Add New Country", driver.findElement(By.cssSelector("td#content h1")).getText());
        }
        driver.findElement(By.cssSelector("span.button-set [name=cancel]")).click();
        Assert.assertEquals("Countries", driver.findElement(By.cssSelector("td#content h1")).getText());
    }
}

