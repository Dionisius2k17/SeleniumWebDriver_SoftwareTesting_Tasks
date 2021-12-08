package ru.stqa.maven;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static java.lang.Class.forName;
import java.io.IOException;
import org.openqa.selenium.NoSuchElementException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import static org.openqa.selenium.Platform.*;

public class TestBasis {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Properties props;

    public boolean isElementPresent (By locator){
        try {//wait.until((WebDriver d)->d.findElement(locator));
            driver.findElement(locator);
            return true;}
        catch (NoSuchElementException ex){return false;}
    }

    protected String getAllowedCharsString() {
        String AllowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        //создаем конструктор строк
        StringBuilder constr = new StringBuilder();
        //создаем генератор чисел
        Random rnd = new Random();
        //цикл записывает 9 символов в конструктор строк
        while (constr.length() < 10) {
            //генерируем индекс
            int index = (int) (rnd.nextFloat() * AllowedChars.length());
            //добавляем символ в конструктор строк
            constr.append(AllowedChars.charAt(index));
        }
        //преобразуем конструктор в строку
        String constrToStr = constr.toString();
        return constrToStr;
    }

    @Before
    public void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        props=new Properties();
        //загружаем в объект props переменные из файла config.properties
        props.load(TestBasis.class.getResourceAsStream("/config.properties"));
        //получаем строку со значением пути к выбранному драйверу
        String driverFromProperties = props.getProperty("driverName");
        //создаем тип, который содержит путь к драйверу
        Class classType = forName(driverFromProperties);
        //через приведение типов создаем новый объект выбранного драйвера
        driver = (WebDriver) classType.newInstance();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void stop()
    {
        driver.quit();
        driver=null;
    }

}