package ru.beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;
    private WebElement login;
    private WebElement passwordButton;
    private WebElement password;
    private WebElement signIn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter login")
    public void enterLogin() {
        login = driver.findElement(By.name("login"));
        login.sendKeys("test-2019-SSU@yandex.ru");
    }

    @Step("Click button for enter password")
    public void clickPasswordButton() {
        passwordButton = driver.findElement(By.className("passp-sign-in-button"));
        passwordButton.click();
    }

    @Step("Enter password")
    public void enterPassword() {
        password = driver.findElement(By.name("passwd"));
        password.sendKeys("test12345");
    }

    @Step("Sign in")
    public void signIn() {
        signIn = driver.findElement(By.className("passp-sign-in-button"));
        signIn.click();
    }
}
