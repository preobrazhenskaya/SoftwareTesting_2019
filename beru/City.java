package ru.beru;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class City extends Account {
    @Test
    public void cityTest () {

        /* Переходим на сайт и закрываем рекламу */
        goToBeru();

        /* Смена города */
        driver.findElement(By
                .xpath("//div[contains(@class, 'unique-selling-proposition-line__region')]" +
                        "//span[contains(@class, 'link__inner')]")).click();
        driver.findElement(By.xpath("(//form[contains(@class, 'region-select-form')]//input)[1]")).click();
        driver.findElement(By.xpath("(//form[contains(@class, 'region-select-form')]//input)[1]"))
                .sendKeys("Хвалынск", Keys.ENTER);
        driver.findElement(By.xpath("//form[contains(@class, 'region-select-form')]" +
                "//div[contains(@class, 'region-suggest__list-item')]")).click();
        driver.findElement(By.xpath("//form[contains(@class, 'region-select-form')]//button")).click();
        Assert.assertTrue(driver.findElements(By.xpath("//span[text() = 'Хвалынск']")).size() > 0);

        /* Вход в аккаунт */
        logIn();

        /* Переход в настройки */
        driver.findElement(By.className("header2-nav__user")).click();
        driver.findElement(By.xpath("//a[text() = 'Настройки']")).click();

        /* Проверка совпадения городов */
        // TODO: сделать скрол, чтобы проверить адрес доставки
        String s1 = driver.findElement(By
                .xpath("//div[contains(@class,'header2__main')]//span[contains(@class, 'link__inner')]")).getText();
        System.out.println(s1);

//        WebElement city2 = driver.findElement(By
//                .xpath("//div[contains(@id,'region')]//span[contains(@class, 'link__inner')]"));
//        Actions actions = new Actions(driver);
//        actions.moveToElement(city2).click().perform();
//        String s2 = city2.getText();
//        System.out.println(s2);


//        WebElement city2 = (new WebDriverWait(driver, 5))
//                .until(ExpectedConditions.presenceOfElementLocated(By
//                        .xpath("//div[contains(@id,'region')]//span[contains(@class, 'link__inner')]")));
//        String s2 = city2.getText();

//        String s2 = driver.findElement(By.xpath("//div[contains(@id,'region')]//span[contains(@class, 'link__inner')]")).getText();
//        System.out.println(s2);
    }
}
