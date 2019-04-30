package ru.beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SettingsPage {
    private WebDriver driver;
    private WebElement firstCityValue;
    private WebElement secondCityValue;
    private WebElement accountButton;
    private WebElement exitButton;

    public SettingsPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Check city equality")
    public void checkCityEquality() {
        firstCityValue = driver.findElement(By.xpath("//div[contains(@class,'header2__main')]" +
                "//span[contains(@class, 'link__inner')]"));
        String firstCityValueStr = firstCityValue.getText();
        driver.get("https://beru.ru/my/settings#region");
        secondCityValue = driver.findElement(By.xpath("//div[contains(@class,'n-headline')]" +
                "//span[contains(@class, 'link__inner')]"));
        String secondCityValueStr = secondCityValue.getText();
        Assert.assertEquals(firstCityValueStr, secondCityValueStr);
    }

    @Step("Open account menu")
    public void openAccount() {
        accountButton = driver.findElement(By.className("header2-nav__user"));
        accountButton.click();
    }

    @Step("Logout")
    public void logOut() {
        exitButton = driver.findElement(By.xpath("//a[text() = 'Выход']"));
        exitButton.click();
    }
}
