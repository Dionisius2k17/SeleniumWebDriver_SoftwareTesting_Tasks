package ru.stqa.maven;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

//17 задание
public class Test17 extends TestBasis {

    @Before
    public void openHomePage(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }


    @Before
    public void start(){

        LoggingPreferences prefs = new LoggingPreferences();
        prefs.enable("browser", Level.ALL);

        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.LOGGING_PREFS, prefs);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void test17() {
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.manage().logs().get("browser").forEach(log -> System.out.println(log));
        List<WebElement> products = driver.findElements(By.cssSelector("a[href*='&product'][title]"));
        for(int a = 0; a < products.size(); a++){
            products.get(a).click();
            String ProductName = driver.findElement(By.cssSelector("input[name='name[en]']")).getAttribute("value");
            List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
            if(logs.size()!=0){
                System.out.println("When we open editor for "+ProductName+" product the following messages are logged in browser:");
                logs.forEach(log -> System.out.println(log));
            }
            driver.navigate().back();
            products = driver.findElements(By.cssSelector("a[href*='&product'][title]"));
        }

    }
}
