package ru.stqa.maven;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestAsAdministrator extends TestBasis{

    @Before
    public void openHomePage(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
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

    /*@Test
    public void testTwo(){
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> countries= driver.findElements(By.cssSelector("tr.row a:not([title])"));
        List<String> countryNames = new ArrayList<>();
        for (int i=0; i<countries.size(); i++){
            String s = countries.get(i).getText();
            countryNames.add(s);
        }
        List<String> defaultCountries = countryNames;
        Collections.sort(countryNames);
        assert countryNames.equals(defaultCountries);
        List<WebElement> getRows= driver.findElements(By.cssSelector("tr.row"));
        for (int i=0; i<getRows.size();i++){
            List<WebElement> rows= driver.findElements(By.cssSelector("tr.row"));
            WebElement row = rows.get(i);
            WebElement zoneVal=row.findElement(By.cssSelector("td:nth-of-type(6)"));
            String s=zoneVal.getText();
            int zoneNum = Integer.parseInt(s);
            if (zoneNum > 0){
                WebElement country=row.findElement(By.cssSelector("a:not([title])"));
                country.click();
                List<WebElement> zones= driver.findElements(By.cssSelector("table#table-zones tr:not(.header) input[type=hidden][value][name*=name]"));
                List<String> zoneNames=new ArrayList<>();
                for (int n=0;n<zones.size();n++){
                    String z=zones.get(n).getText();
                    zoneNames.add(z);
                }
                List<String> zoneDefault=zoneNames;
                Collections.sort(zoneNames);
                assert zoneNames.equals(zoneDefault);
                driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
            } else {
                continue;
            }
        }
    }

    @Test
    public void testThree(){
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement> countries=driver.findElements(By.cssSelector("tr.row a:not([title])"));
        for (int i=0; i<countries.size(); i++){
            List<WebElement> countryNames=driver.findElements(By.cssSelector("tr.row a:not([title])"));
            countryNames.get(i).click();
            List<WebElement> zones=driver.findElements(By.cssSelector("select[name*=zone_code]"));
            List<String> zoneList=new ArrayList<>();
            for (int n=0; n<zones.size(); n++){
                String zoneName=zones.get(n).getText();
                zoneList.add(zoneName);
            }
            List<String> zoneDefault=zoneList;
            Collections.sort(zoneList);
            assert zoneDefault.equals(zoneList);
            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        }
    }*/
}