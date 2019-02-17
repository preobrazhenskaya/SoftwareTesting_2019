package ru.beru;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Login extends WebDriverSettings {
    @Test
    public void loginTest() {

        /* Переход на сайт beru.ru */
        driver.get("https://beru.ru");

        /* Закрытие рекламы */
        WebElement closeAds = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("_1ZYDKa22GJ")));
        closeAds.click();

        /* Авторизация */
        driver.findElement(By.className("header2-user")).click();
        driver.findElement(By.name("login")).sendKeys("test-2019-SSU@yandex.ru");
        driver.findElement(By.className("passp-sign-in-button")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.name("passwd")).sendKeys("test12345");
        driver.findElement(By.className("passp-sign-in-button")).click();

        /* Проверяем сменилась ли надпись "Войти в аккаунт" на "Мой профиль" */
        Assert.assertTrue(driver.findElements(By.xpath("//span[text() = 'Мой профиль']")).size() > 0);
        //Assert.assertTrue(driver.findElements(By.xpath("//span[text() = 'Войти в аккаунт']")).size() == 0);

        /* Возвращаем систему в исходное состояние */
        driver.findElement(By.className("header2-nav__user")).click();
        driver.findElement(By.xpath("/html/body/div[7]/div[2]/div/ul[4]/li[2]")).click();
    }
}