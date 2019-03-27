package ru.beru;

import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class Account extends Settings {

    /* Авторизация */
    public void logIn() {
        driver.findElement(By.className("header2-user")).click();
        driver.findElement(By.name("login")).sendKeys("test-2019-SSU@yandex.ru");
        driver.findElement(By.className("passp-sign-in-button")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.name("passwd")).sendKeys("test12345");
        driver.findElement(By.className("passp-sign-in-button")).click();
    }

    /* Выход из аккаунта / возврат системы в исходное состояние */
    public void logOut() {
        driver.findElement(By.className("header2-nav__user")).click();
        driver.findElement(By.xpath("//a[text() = 'Выход']")).click();
    }
}