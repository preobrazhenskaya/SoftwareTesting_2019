package ru.beru;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class City extends Account {
    @Test(dataProvider = "City")
    public void cityTest (String cityName) {

        /* Переходим на сайт и закрываем рекламу */
        goToBeru();

        /* Смена города */
        WebElement cityButton = driver.findElement(By
                .xpath("//div[contains(@class, 'unique-selling-proposition-line__region')]" +
                        "//span[contains(@class, 'link__inner')]"));
        highlighter(driver, cityButton);
        cityButton.click();

        WebElement cityInput = driver.findElement(By
                .xpath("(//form[contains(@class, 'region-select-form')]//input)[1]"));
        highlighter(driver, cityInput);
        cityInput.click();
        cityInput.sendKeys(cityName, Keys.ENTER);

        WebElement cityList = driver.findElement(By
                .xpath("//form[contains(@class, 'region-select-form')]" +
                        "//div[contains(@class, 'region-suggest__list-item')]"));
        highlighter(driver, cityList);
        cityList.click();

        WebElement cityEnter = driver.findElement(By.xpath("//form[contains(@class, 'region-select-form')]//button"));
        highlighter(driver, cityEnter);
        cityEnter.click();
        driver.navigate().refresh();

        WebElement check = driver.findElement(By.cssSelector(".link__inner"));
        highlighter(driver, check);
        String cityN = check.getAttribute("textContent");
        Assert.assertEquals(cityN, cityName);

        /* Вход в аккаунт */
        logIn();

        /* Переход в настройки */
        WebElement account = driver.findElement(By.className("header2-nav__user"));
        highlighter(driver, account);
        account.click();

        WebElement settings = driver.findElement(By.xpath("//a[text() = 'Настройки']"));
        highlighter(driver, settings);
        settings.click();

        /* Проверка совпадения городов */
        WebElement city1 = driver.findElement(By
                .xpath("//div[contains(@class,'header2__main')]//span[contains(@class, 'link__inner')]"));
        highlighter(driver, city1);
        String c1 = city1.getText();

        driver.get("https://beru.ru/my/settings#region");
        WebElement city2 = driver.findElement(By
                .xpath("//div[contains(@class,'n-headline')]//span[contains(@class, 'link__inner')]"));
        highlighter(driver, city2);
        String c2 = city2.getText();
        Assert.assertEquals(c1, c2);

        /* Возвращаем систему в исходное состояние */
        logOut();
    }
}
