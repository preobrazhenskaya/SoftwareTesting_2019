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
        WebElement price1 = driver.findElement(By.name("Цена от"));
        highlighter(driver, price1);
        price1.sendKeys("999");

        WebElement price2 = driver.findElement(By.name("Цена до"));
        highlighter(driver, price2);
        price2.sendKeys("1999");
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

        /* Добавление щетки в корзину, переход в корзину */
        List<WebElement> brushes = driver.findElements(By.cssSelector(".l3daMzINHn>button"));
        WebElement brush = brushes.get(brushes.size() - 2);
        highlighter(driver, brush);
        while (true) {
            if (driver.findElements(By
                    .xpath("//span[contains(@class, '_2w0qPDYwej') and contains(text(), 'Перейти в корзину')]"))
                    .size() > 0) {
                WebElement basket = driver.findElement(By
                        .xpath("//span[contains(@class, '_2w0qPDYwej') and contains(text(), 'Перейти в корзину')]"));
                highlighter(driver, basket);
                basket.click();
                break;
            } else {
                brush.click();
            }
        }

        /* Проверка цен */
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By
                .xpath("//span[contains(@class, '_2w0qPDYwej') and contains(text(), 'Применить')]")));
        WebElement rem = driver.findElement(By
                .xpath("//span[contains(@class, '_3EX9adn_xp')]//span[contains(@class, 'voCFmXKfcL')]"));
        highlighter(driver, rem);
        String rem_str = rem.getText();
        int rem_int = Integer.valueOf(rem_str.replaceAll("\\D+",""));
        Assert.assertTrue(rem_int > 0);

        WebElement tooth_price = driver.findElement(By.xpath("//span[text() = 'Товары']/following-sibling::span"));
        highlighter(driver, tooth_price);
        String tp_str = tooth_price.getText();
        int tp_int = Integer.valueOf(tp_str.replaceAll("\\D+",""));

        WebElement delivery = driver.findElement(By
                .xpath("//span[text() = 'Доставка курьером']/following-sibling::span"));
        highlighter(driver, delivery);
        String d_str = delivery.getText();
        int d_int = Integer.valueOf(d_str.replaceAll("\\D+",""));

        WebElement total_price = driver.findElement(By.xpath("//span[text() = 'Итого']/following-sibling::span"));
        highlighter(driver, total_price);
        String total_str = total_price.getText();
        int total_int = Integer.valueOf(total_str.replaceAll("\\D+",""));
        Assert.assertEquals(tp_int + d_int, total_int);

        /* Добавление щеток, пока сумма заказа < 3000р */
        int free = 3000 - tp_int;
        int count = free / tp_int;
        if (free % tp_int != 0) {
            count++;
        }
        for (int i = 0; i < count; i++) {
            WebElement plus = driver.findElement(By
                    .xpath("//span[contains(@class, 'jE8-ezGMzW') and contains(text(), '+')]"));
            highlighter(driver, plus);
            plus.click();
        }

        /* Проверка цен */
        try {
            Thread.sleep(2000);
        } catch (Exception e) {}
        highlighter(driver, tooth_price);
        tp_str = tooth_price.getText();
        tp_int = Integer.valueOf(tp_str.replaceAll("\\D+",""));

        highlighter(driver, total_price);
        total_str = total_price.getText();
        total_int = Integer.valueOf(total_str.replaceAll("\\D+",""));
        Assert.assertEquals(tp_int, total_int);
    }
}