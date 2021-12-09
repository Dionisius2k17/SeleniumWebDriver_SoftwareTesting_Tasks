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

        //@Before
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

}



