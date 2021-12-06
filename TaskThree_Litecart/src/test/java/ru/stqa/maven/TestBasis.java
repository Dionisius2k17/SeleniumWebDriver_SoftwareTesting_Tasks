package ru.stqa.maven;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static java.lang.Class.forName;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;

public class TestBasis {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Properties properties;

    public boolean isElementPresent (By locator){
        try {
            driver.findElement(locator);
            return true;
        }
        catch (NoSuchElementException ex){
            return false;
        }
    }

    /*
    загружаем в объект props переменные из файла config.properties
    получаем строку со значением пути к выбранному драйверу
    создаем тип, который содержит путь к драйверу
    через приведение типов создаем новый объект выбранного драйвера
    */

    @Before
    public void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        properties=new Properties();
        properties.load(TestBasis.class.getResourceAsStream("/config.properties"));
        String driverFromProperties = properties.getProperty("driverName");
        System.out.println("This driver is now used: " + driverFromProperties);
        Class classType = forName(driverFromProperties);
        driver = (WebDriver) classType.newInstance();
        wait = new WebDriverWait(driver, 10);
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @After
    public void stop()
    {
        driver.quit();
        driver=null;
    }
}
