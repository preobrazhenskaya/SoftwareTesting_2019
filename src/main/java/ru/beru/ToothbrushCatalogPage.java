package ru.beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ToothbrushCatalogPage {
    private WebDriver driver;
    private WebElement minPrice;
    private WebElement maxPrice;
    private List<WebElement> prices;
    private List<WebElement> brushes;
    private WebElement rightBrush;
    private WebElement basket;

    public ToothbrushCatalogPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter min price")
    public void enterMinPrice() {
        minPrice = driver.findElement(By.name("Цена от"));
        minPrice.sendKeys("999");
    }

    @Step("Enter max price")
    public void enterMaxPrice() {
        maxPrice = driver.findElement(By.name("Цена до"));
        maxPrice.sendKeys("1999");
    }

    @Step("Check toothbrush prices")
    public void checkBrushPrices() {
        prices = driver.findElements(By.xpath("//span[contains(@class, '_2C1xHb-RDU')]/preceding-sibling::span"));
        List<Integer> pricesInt = new ArrayList<Integer>();
        for (int i = 0; i < prices.size(); i++) {
            pricesInt.add(Integer.valueOf(prices.get(i).getText().replaceAll("\\D+","")));
        }
        for (int i = 0; i < prices.size(); i++) {
            Assert.assertTrue(pricesInt.get(i) <= 1999 || pricesInt.get(i) >= 999);
        }
    }

    @Step("Add toothbrush")
    public void addBrush() {
        brushes = driver.findElements(By.cssSelector(".l3daMzINHn>button"));
        rightBrush = brushes.get(brushes.size() - 2);
        do {
            rightBrush.click();
            new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By
                    .xpath("//div[contains(@class, 'l3daMzINHn')]//button")));
        } while (driver.findElements(By
                    .xpath("//span[contains(@class, '_2w0qPDYwej') and contains(text(), 'Перейти в корзину')]"))
                    .size() <= 0);
    }

    @Step("Open basket")
    public void openBasket() {
        basket = driver.findElement(By.xpath("//span[contains(@class, '_2w0qPDYwej') and contains(text(), " +
                "'В корзине')]"));
        basket.click();
    }
}
