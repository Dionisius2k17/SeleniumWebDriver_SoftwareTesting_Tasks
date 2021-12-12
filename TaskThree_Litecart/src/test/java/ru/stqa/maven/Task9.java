package ru.stqa.maven;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class Task9 extends TestBasis {
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
                //System.out.println(countryZones);
                driver.navigate().back();
                countriesZonesTable = driver.findElement(By.cssSelector("table.dataTable"));
                allRowsCountriesZones = countriesZonesTable.findElements(By.cssSelector("tr"));
            }
        }
    }

    //@Test
    public void test9Part2() throws Exception{
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement> list = driver.findElements(By.cssSelector("li#app-"));
        list.get(5).click();

        List<String> zones = new ArrayList<>();
        List<String> sortedZones = new ArrayList<>();
        List<WebElement> allRowsZones = driver.findElements(By.cssSelector("table.dataTable tr"));
        int iter = allRowsZones.size();
        for (int i = 1; i < iter - 1; i++) {
            allRowsZones = driver.findElements(By.cssSelector("table.dataTable tr"));
            List<WebElement> cellsZones = allRowsZones.get(i).findElements(By.cssSelector("td"));
            cellsZones.get(2).findElement(By.tagName("a")).click();
            List<WebElement> rowsGeoZones = driver.findElements(By.cssSelector("table#table-zones tr"));
            int iterRow = rowsGeoZones.size();
            zones.clear();
            sortedZones.clear();
            for (int j = 1; j < iterRow - 1; j++) {
                rowsGeoZones = driver.findElements(By.cssSelector("table#table-zones tr"));
                List<WebElement> cellsZonesEdit = rowsGeoZones.get(j).findElements(By.cssSelector("td"));
                String str = cellsZonesEdit.get(2).findElement(By.cssSelector("select")).getAttribute("value");
                zones.add(cellsZonesEdit.get(2).findElement(By.cssSelector("[value=" + str + "]")).getAttribute("textContent"));
            }
            sortedZones.addAll(zones);
            Collections.sort(sortedZones);
            Assert.assertEquals(sortedZones, zones);
            if (zones.equals(sortedZones) == false){
                throw new Exception("Zones are not ordered by an alphabetical order");
            }
            driver.navigate().back();
        }

    }
}
