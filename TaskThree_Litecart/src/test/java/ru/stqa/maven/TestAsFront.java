package ru.stqa.maven;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TestAsFront extends TestBasis{
    /*
    В функции ниже находим список всех изображений, создаётся цикл для их перебора.
    Для каждого из таких изображений необходимо будет получить список стикеров, после чего проверяем их количество.

    */
    @Test
    public void testOne(){
        driver.get("http://localhost/litecart/");
        List<WebElement> images = driver.findElements(By.cssSelector("li.product"));
        for (int i=0; i<images.size();i++){
            List<WebElement> sticker= images.get(i).findElements(By.cssSelector("div.image-wrapper>div[class*=sticker]"));
            assert sticker.size()==1;
        }
    }
}
