package ru.beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ChangeCityPage {
    private WebDriver driver;
    private WebElement cityInput;
    private WebElement menuItem;
    private WebElement cityEnter;

    public ChangeCityPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter new city")
    public void enterCity(String cityName) {
        cityInput = driver.findElement(By.xpath("(//form[contains(@class, 'region-select-form')]//input)[1]"));
        cityInput.click();
        cityInput.sendKeys(cityName, Keys.ENTER);
    }

    @Step("Click the menu item")
    public void clickMenuItem() {
        menuItem = driver.findElement(By.xpath("//form[contains(@class, 'region-select-form')]" +
                "//div[contains(@class, 'region-suggest__list-item')]"));
        menuItem.click();
    }

    @Step("Click button for save new city")
    public void saveCity() {
        cityEnter = driver.findElement(By.xpath("//form[contains(@class, 'region-select-form')]//button"));
        cityEnter.click();
        driver.navigate().refresh();
    }
}
