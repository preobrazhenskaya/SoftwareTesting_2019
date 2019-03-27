package ru.beru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Toothbrush extends Account {
    @Test
    public void toothBrushTest () {
        /* Переход на сайт и закрываем рекламу */
        goToBeru();

        /* Переход в раздел электрических щеток */
        driver.get("https://beru.ru/catalog/elektricheskie-zubnye-shchetki/80961/list?hid=278374&track=menuleaf");

        /* Устанавление ценового диапазона */
        driver.findElement(By.name("Цена от")).sendKeys("999");
        driver.findElement(By.name("Цена до")).sendKeys("1999");
        driver.navigate().refresh();

        /* Проверка попадания щеток в ценовой диапазон */
        List<WebElement> price = driver.findElements(By
                .xpath("//span[contains(@class, '_2C1xHb-RDU')]/preceding-sibling::span"));
        List<Integer> price_int = new ArrayList<Integer>();
        for (int i = 0; i < price.size(); i++) {
            price_int.add(Integer.valueOf(price.get(i).getText().replaceAll("\\D+","")));
        }
        for (int i = 0; i < price.size(); i++) {
            Assert.assertTrue(price_int.get(i) <= 1999 || price_int.get(i) >= 999);
        }

        /* Добавление щетки в корзину */
        List<WebElement> brushes = driver.findElements(By.cssSelector(".l3daMzINHn>button"));
        brushes.get(brushes.size() - 2).click();

        /* Переход в корзину */
        new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfElementsToBeMoreThan(By
                .xpath("//span[contains(@class, '_2w0qPDYwej') and contains(text(), 'Перейти в корзину')]"), 0));
        driver.get("https://beru.ru/my/cart?track=head");

        /* Проверка цен */
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By
                .xpath("//span[contains(@class, '_2w0qPDYwej') and contains(text(), 'Применить')]")));
        String rem = driver.findElement(By
                .xpath("//span[contains(@class, '_3EX9adn_xp')]//span[contains(@class, 'voCFmXKfcL')]")).getText();
        int rem_int = Integer.valueOf(rem.replaceAll("\\D+",""));
        Assert.assertTrue(rem_int > 0);
        String tooth_price = driver.findElement(By.xpath("//span[text() = 'Товары']/following-sibling::span")).getText();
        int tp_int = Integer.valueOf(tooth_price.replaceAll("\\D+",""));
        String delivery =
                driver.findElement(By.xpath("//span[text() = 'Доставка курьером']/following-sibling::span")).getText();
        int d_int = Integer.valueOf(delivery.replaceAll("\\D+",""));
        String total_price= driver.findElement(By.xpath("//span[text() = 'Итого']/following-sibling::span")).getText();
        int total_int = Integer.valueOf(total_price.replaceAll("\\D+",""));
        Assert.assertEquals(tp_int + d_int, total_int);

        /* Добавление щеток, пока сумма заказа < 3000р */
        int free = 3000 - tp_int;
        int count = free / tp_int;
        if (free % tp_int != 0) {
            count++;
        }
        for (int i = 0; i < count; i++) {
            driver.findElement(By.xpath("//span[contains(@class, 'jE8-ezGMzW') and contains(text(), '+')]")).click();
        }

        /* Проверка цен */
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
        tooth_price = driver.findElement(By.xpath("//span[text() = 'Товары']/following-sibling::span")).getText();
        tp_int = Integer.valueOf(tooth_price.replaceAll("\\D+",""));
        total_price= driver.findElement(By.xpath("//span[text() = 'Итого']/following-sibling::span")).getText();
        total_int = Integer.valueOf(total_price.replaceAll("\\D+",""));
        Assert.assertEquals(tp_int, total_int);
    }
}