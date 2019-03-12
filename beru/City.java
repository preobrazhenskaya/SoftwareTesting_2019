package ru.beru;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;


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
        String c1 = driver.findElement(By
                .xpath("//div[contains(@class,'header2__main')]//span[contains(@class, 'link__inner')]")).getText();
        driver.get("https://beru.ru/my/settings#region");
        String c2 = driver.findElement(By
                .xpath("//div[contains(@class,'n-headline')]//span[contains(@class, 'link__inner')]")).getText();
        Assert.assertTrue(c1.equals(c2));

        /* Возвращаем систему в исходное состояние */
        logOut();
    }
}
