package ru.stqa.maven;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
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
        driver.findElement(By.cssSelector("[href*=countries]")).click();
        driver.findElement(By.className("button")).click();
        //получаем id основного окна и создаем список ссылочных элементов
        String initialWindow= driver.getWindowHandle();
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
            driver.switchTo().window(initialWindow);
            //кликаем по ссылке, ожидаем появление окна
            element.click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            //получаем id окон и удаляем id основного окна из списка
            Set<String> handles = driver.getWindowHandles();
            handles.remove(initialWindow);
            //сохраняем id нового окна в переменную
            String newWindow = handles.iterator().next();
            //переключаемся на новое окно
            driver.switchTo().window(newWindow);
            //закрываем и переключаемся на основное окно
            driver.close();
            driver.switchTo().window(initialWindow);
        }
    }
}
