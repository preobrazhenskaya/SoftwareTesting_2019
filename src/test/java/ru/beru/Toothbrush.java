package ru.beru;

import org.testng.annotations.Test;

public class Toothbrush extends Settings {
    @Test
    public void toothBrushTest () {
        ToothbrushCatalogPage toothbrushCatalogPage = new ToothbrushCatalogPage(driver);
        BasketPage basketPage = new BasketPage(driver);

        /* Переход в раздел электрических щеток */
        driver.get("https://beru.ru/catalog/elektricheskie-zubnye-shchetki/80961/list?hid=278374&track=menuleaf");

        /* Устанавление ценового диапазона */
        toothbrushCatalogPage.enterMinPrice();
        toothbrushCatalogPage.enterMaxPrice();

        /* Проверка попадания щеток в ценовой диапазон */
        toothbrushCatalogPage.checkBrushPrices();

        /* Добавление щетки в корзину, переход в корзину */
        toothbrushCatalogPage.addBrush();
        toothbrushCatalogPage.openBasket();

        /* Проверка цен */
        basketPage.checkPrices();

        /* Добавление щеток, пока сумма заказа < 3000р */
        basketPage.addBrushes();

        /* Проверка цен */
        basketPage.checkPrices();
    }
}