package ru.beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {
    private WebDriver driver;
    private WebElement ads;
    private WebElement signInButton;
    private WebElement accountButton;
    private WebElement exitButton;
    private WebElement cityButton;
    private WebElement cityName;
    private WebElement settingsButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Close ads")
    public void closeAds() {
        ads = (new WebDriverWait(driver, 5)).until(ExpectedConditions.presenceOfElementLocated(By
                .className("_1ZYDKa22GJ")));
        ads.click();
    }

    @Step("Click button for sign in account")
    public void clickAccountButton() {
        signInButton = driver.findElement(By.className("header2-user"));
        signInButton.click();
    }

    @Step("Check account text")
    public void checkAccountText() {
        accountButton = driver.findElement(By.className("header2-nav__user"));
        String accountText = accountButton.getText();
        Assert.assertEquals(accountText, "Мой профиль");
    }

    public boolean checkLoggedIn() {
        accountButton = driver.findElement(By.className("header2-nav__user"));
        String accountText = accountButton.getText();
        return accountText.equals("Мой профиль");
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

    @Step("Go to the page with change of the city")
    public void goToChangeCityPage() {
        cityButton = driver.findElement(By.xpath("//div[contains(@class, 'unique-selling-proposition-line__region')]" +
                        "//span[contains(@class, 'link__inner')]"));
        cityButton.click();
    }

    @Step("Check city")
    public void checkCity(String newCity) {
        cityName = driver.findElement(By.cssSelector(".link__inner"));
        String cityNameStr = cityName.getAttribute("textContent");
        Assert.assertEquals(newCity, cityNameStr);
    }

    @Step("Open settings")
    public void openSettings() {
        settingsButton = driver.findElement(By.xpath("//a[text() = 'Настройки']"));
        settingsButton.click();
    }
}
