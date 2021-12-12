package ru.stqa.maven;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test10 extends TestBasis{

    //@Before
    public void openHomePage(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    /*
     * Получаем текст в строку, исходную цену (без учёта скидки) и ее цвет, аналогично проходимся и по акционной цене
     * */
    //@Test
    public void test10() {
        WebElement yd = driver.findElement(By.cssSelector("#box-campaigns .name"));
        String firstDuck = yd.getText();
        WebElement originalPrice = driver.findElement(By.cssSelector("#box-campaigns .regular-price"));
        String oPrice = originalPrice.getText();
        String colorOriginalPrice = driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getCssValue("color");
        List<String> colorOriginalMatches = new ArrayList<String>();
        Matcher matcher = Pattern.compile("\\d+").matcher(colorOriginalPrice);
        while (matcher.find())
            colorOriginalMatches.add(matcher.group());
        int r = Integer.parseInt(colorOriginalMatches.get(0));
        int g = Integer.parseInt(colorOriginalMatches.get(1));
        int b = Integer.parseInt(colorOriginalMatches.get(2));
        assert r == g && g == b;
        String deleted = driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getCssValue("text-decoration-line");
        String line = "line-through";
        assert deleted.equals(line);


        WebElement priceDiscount = driver.findElement(By.cssSelector("#box-campaigns .campaign-price"));
        String cPrice = priceDiscount.getText();
        assert isElementPresent(By.cssSelector("#box-campaigns strong.campaign-price"));
        String colorPriceDiscount = driver.findElement(By.cssSelector("#box-campaigns .campaign-price")).getCssValue("color");
        List<String> colorDiscountMatches = new ArrayList<String>();
        Matcher discountMatcher = Pattern.compile("\\d+").matcher(colorPriceDiscount);
        while (discountMatcher.find())
            colorDiscountMatches.add(discountMatcher.group());
        int gd = Integer.parseInt(colorDiscountMatches.get(1));
        int bd = Integer.parseInt(colorDiscountMatches.get(2));
        assert gd == 0 && bd == 0;

        //получаем параметры (высота и ширина) обеих цен
        String heightOriginalPrice = driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getAttribute("offsetHeight");
        int heightRPrice = Integer.parseInt(heightOriginalPrice);
        String widthOriginalPrice = driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getAttribute("offsetWidth");
        int widthRPrice = Integer.parseInt(widthOriginalPrice);
        String heightDiscountPrice = driver.findElement(By.cssSelector("#box-campaigns .campaign-price")).getAttribute("offsetHeight");
        int heightCPrice = Integer.parseInt(heightDiscountPrice);
        String widthDiscountPrice = driver.findElement(By.cssSelector("#box-campaigns .campaign-price")).getAttribute("offsetWidth");
        int widthCPrice = Integer.parseInt(widthDiscountPrice);
        assert heightCPrice > heightRPrice;
        assert widthCPrice > widthRPrice;

        yd.click();
        WebElement duckName = driver.findElement(By.cssSelector("h1.title"));
        String headerName = duckName.getText();
        WebElement origPrice = driver.findElement(By.cssSelector(".regular-price"));
        String oPriceCard = origPrice.getText();

        String colorOriginalPriceCard = driver.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        List<String> colorOriginalCardMatches = new ArrayList<String>();
        Matcher matcherCard = Pattern.compile("\\d+").matcher(colorOriginalPrice);
        while (matcherCard.find())
            colorOriginalCardMatches.add(matcherCard.group());
        int rCard = Integer.parseInt(colorOriginalCardMatches.get(0));
        int gCard = Integer.parseInt(colorOriginalCardMatches.get(1));
        int bCard = Integer.parseInt(colorOriginalCardMatches.get(2));
        assert rCard == gCard && gCard == bCard;

        String deletedCard = driver.findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration-line");
        String lineCard = "line-through";
        assert deletedCard.equals(lineCard);

        WebElement discPrice = driver.findElement(By.cssSelector(".campaign-price"));
        String cPriceCard = discPrice.getText();

        assert isElementPresent(By.cssSelector("strong.campaign-price"));

        String colorDiscountPrice = driver.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        List<String> colorDiscountMatchesCard = new ArrayList<String>();
        Matcher discountMatcherCard = Pattern.compile("\\d+").matcher(colorDiscountPrice);
        while (discountMatcherCard.find())
            colorDiscountMatchesCard.add(discountMatcherCard.group());
        int gDiscountCard = Integer.parseInt(colorDiscountMatchesCard.get(1));
        int bDiscountCard = Integer.parseInt(colorDiscountMatchesCard.get(2));
        assert gDiscountCard == 0 && bDiscountCard == 0;

        assert firstDuck.equals(headerName);
        assert oPrice.equals(oPriceCard);
        assert cPrice.equals(cPriceCard);

        //получаем размер шрифта цен
        String sizeOriginalPriceCard = driver.findElement(By.cssSelector(".regular-price")).getCssValue("font-size")
                .replaceAll("px", "");
        int sizeOPriceCard = Integer.parseInt(sizeOriginalPriceCard);
        String sizeDiscountPriceCard = driver.findElement(By.cssSelector(".campaign-price")).getCssValue("font-size")
                .replaceAll("px", "");
        int sizeDPriceCard = Integer.parseInt(sizeDiscountPriceCard);

        //проверяем шрифты
        assert sizeDPriceCard > sizeOPriceCard;
    }
}
