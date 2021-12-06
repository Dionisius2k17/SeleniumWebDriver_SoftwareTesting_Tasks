package ru.stqa.maven;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestAsFront extends TestBasis{

    @Before
    public void start(){
        driver.get("http://localhost/litecart/");
    }


    /*
    В функции ниже находим список всех изображений, создаётся цикл для их перебора.
    Для каждого из таких изображений необходимо будет получить список стикеров, после чего проверяем их количество.
    */
    @Test
    public void testOne(){
        List<WebElement> images = driver.findElements(By.cssSelector("li.product"));
        for (int i = 0; i<images.size(); i++){
            List<WebElement> sticker= images.get(i).findElements(By.cssSelector("div.image-wrapper>div.sticker"));
            assert sticker.size() == 1;
        }
    }
}