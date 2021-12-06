package ru.stqa.maven;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Tests extends TestBasis {


    /*
    Сначала находим меню и список элементов в нём по имени класса.
    Во внешнем цикле производится перебор элементов списка. В каждой его итерации
    заново находим список, но уже по CSS селектору, перебираем элементы списка и кликаем,
    проверяем наличие заголовка (тег h1), находим в каждом пункте список подпунктов.
    Во внутреннем цикле (для перебора подпунктов) проходимся по каждой итерации, при
    которой заново находим список подпунктов, кликаем по нему и проверяем наличик заголовка
    */
    @Test
    public void firstTest() {
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

