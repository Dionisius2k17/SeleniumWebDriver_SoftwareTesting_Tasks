package ru.stqa.maven;

import org.openqa.selenium.By;

public class Test11 extends TestBasis{
    //@Before
    public void openHomePage(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }
}
