package ru.beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasketPage {
    private WebDriver driver;
    private WebElement brushPriceLeft;
    private WebElement brushPriceRight;
    private WebElement deliveryPrice;
    private WebElement discount;
    private WebElement totalPrice;
    private WebElement plus;
    private int brushPriceLeftInt;
    private int brushPriceRightInt;
    private int brushPriceWithDiscount;
    private int deliveryPriceInt;
    private int totalPriceInt;


    public BasketPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Check prices")
    public void checkPrices() {
        /* Ожидание надписи "бесплатно", если доставка бесплатна, либо кликабельности кнопки "Применить ..." */
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.
                            visibilityOfAllElementsLocatedBy(By.xpath("//span[text()='бесплатно']")));
        } catch (TimeoutException e) {
            new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By
                    .xpath("//span[contains(@class, '_2w0qPDYwej') and contains(text(), 'Применить')]")));
        }

        /* Берем цену щетки в левом окне */
        brushPriceLeft = driver.findElement(By.xpath("//span[contains(@class, '_1JLs4_hnVR')]"));
        brushPriceLeftInt = Integer.valueOf(brushPriceLeft.getText().replaceAll("\\D+",""));

        /* Берем скидку, если она есть, иначе присваимваем ей 0 */
        int discountInt;
        try {
            discount = driver.findElement(By.xpath("//span[text() = 'Скидка на товары']/following-sibling::span"));
            discountInt = Integer.valueOf(discount.getText().replaceAll("\\D+",""));
        } catch (Exception e) {
            discountInt = 0;
        }

        /* Берем цену щетки в правом окне и учитываем скидку*/
        brushPriceRight = driver.findElement(By.xpath("//span[text() = 'Товары']/following-sibling::span"));
        brushPriceRightInt = Integer.valueOf(brushPriceRight.getText().replaceAll("\\D+",""));
        brushPriceWithDiscount = brushPriceRightInt - discountInt;

        /* Проверяем эти 2 цены на равенство */
        Assert.assertEquals(brushPriceLeftInt, brushPriceWithDiscount);

        /* Берем цену доставки */
        deliveryPrice = driver.findElement(By.xpath("//span[text() = 'Доставка курьером']/following-sibling::span"));
        String deliveryPriceStr = deliveryPrice.getText().replaceAll("\\D+", "");
        if (deliveryPriceStr.equals("")) {
            deliveryPriceInt = 0;
        } else {
            deliveryPriceInt = Integer.valueOf(deliveryPriceStr);
        }

        /* Берем итоговую цену */
        totalPrice = driver.findElement(By.xpath("//span[text() = 'Итого']/following-sibling::span"));
        totalPriceInt = Integer.valueOf(totalPrice.getText().replaceAll("\\D+",""));

        /* Проверяем на равенство итоговую цену и цену, которая получается при сложении доставки и цены щетки */
        Assert.assertEquals(brushPriceWithDiscount + deliveryPriceInt, totalPriceInt);
    }

    @Step("Add toothbrush while no free delivery")
    public void addBrushes() {
        int free = 3000 - brushPriceRightInt;
        int count = free / brushPriceRightInt;
        if (free % brushPriceRightInt != 0) {
            count++;
        }
        for (int i = 0; i < count; i++) {
            plus = driver.findElement(By.xpath("//span[contains(@class, 'jE8-ezGMzW') and contains(text(), '+')]"));
            plus.click();
        }
    }
}
