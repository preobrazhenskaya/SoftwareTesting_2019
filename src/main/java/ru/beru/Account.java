package ru.beru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class Account extends Settings {

    /* Авторизация */
    public void logIn() {
        WebElement account_button = driver.findElement(By.className("header2-user"));
        highlighter(driver, account_button);
        account_button.click();

        WebElement login = driver.findElement(By.name("login"));
        highlighter(driver, login);
        login.sendKeys("test-2019-SSU@yandex.ru");

        WebElement pass_button = driver.findElement(By.className("passp-sign-in-button"));
        highlighter(driver, pass_button);
        pass_button.click();

        WebElement pass = driver.findElement(By.name("passwd"));
        highlighter(driver, pass);
        pass.sendKeys("test12345");

        WebElement sign_in = driver.findElement(By.className("passp-sign-in-button"));
        highlighter(driver, sign_in);
        sign_in.click();
    }

    /* Выход из аккаунта / возврат системы в исходное состояние */
    public void logOut() {
        WebElement account = driver.findElement(By.className("header2-nav__user"));
        highlighter(driver, account);
        account.click();

        WebElement exit = driver.findElement(By.xpath("//a[text() = 'Выход']"));
        highlighter(driver, exit);
        exit.click();
    }
}