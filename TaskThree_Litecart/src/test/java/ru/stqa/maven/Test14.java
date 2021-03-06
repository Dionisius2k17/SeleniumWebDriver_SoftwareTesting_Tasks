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
    public void test9Part1() throws Exception{
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> list = driver.findElements(By.cssSelector("li#app-"));
        list.get(2).click();
        List<String> countriesList = new ArrayList<>();
        WebElement countriesTable = driver.findElement(By.cssSelector("table.dataTable"));
        List<WebElement> allRowsCountries = countriesTable.findElements(By.cssSelector("tr"));
        for (int i = 1; i < allRowsCountries.size() - 1; i++) {
            List<WebElement> cellsCountries = allRowsCountries.get(i).findElements(By.cssSelector("td"));
            countriesList.add(cellsCountries.get(4).getAttribute("textContent"));
        }
        List<String> sortedCountries = new ArrayList<>();
        sortedCountries.addAll(countriesList);
        Collections.sort(sortedCountries);
        Assert.assertEquals(sortedCountries, countriesList);
        if (countriesList.equals(sortedCountries) == false){
            throw new Exception("Countries are not sorted by alphabethical order");
        }
        List<String> countryZones = new ArrayList<>();
        WebElement countriesZonesTable = driver.findElement(By.cssSelector("table.dataTable"));
        List<WebElement> allRowsCountriesZones = countriesZonesTable.findElements(By.cssSelector("tr"));
        int iterations = allRowsCountriesZones.size();
        countriesList.clear();
        for (int i = 1; i < iterations - 1; i++) {
            List<WebElement> cellsZones = allRowsCountriesZones.get(i).findElements(By.cssSelector("td"));
            int zone = Integer.parseInt(cellsZones.get(5).getAttribute("textContent"));
            if (zone > 0) {
                cellsZones.get(4).findElement(By.tagName("a")).click();
                WebElement tableZones = driver.findElement(By.cssSelector("table#table-zones"));
                List<WebElement> allRowsZones = tableZones.findElements(By.cssSelector("tr"));
                for (int j = 1; j < allRowsZones.size() - 1; j++) {
                    List<WebElement> cellsZone = allRowsZones.get(j).findElements(By.cssSelector("td"));
                    countryZones.add(cellsZone.get(2).getAttribute("textContent"));
                }
                ArrayList<String> sortedCountriesZones = new ArrayList<>();
                sortedCountriesZones = (ArrayList<String>) countryZones;
                Collections.sort(sortedCountriesZones);
                Assert.assertEquals(sortedCountriesZones, countryZones);
                System.out.println(countryZones);
                driver.navigate().back();
                countryZones.clear();
                System.out.println(countryZones);
                sortedCountriesZones.clear();
                countriesZonesTable = driver.findElement(By.cssSelector("table.dataTable"));
                allRowsCountriesZones = countriesZonesTable.findElements(By.cssSelector("tr"));
            }
        }
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

