package ru.beru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class Login extends Account {
    @Test
    public void loginTest() {

        /* Переходим на сайт и закрываем рекламу */
        goToBeru();

        /* Вход в аккаунт */
        logIn();

        /* Проверяем сменилась ли надпись "Войти в аккаунт" на "Мой профиль" */
        WebElement account = driver.findElement(By.className("header2-nav__user"));
        String acc_text = account.getText();
        Assert.assertEquals(acc_text, "Мой профиль");

        /* Возвращаем систему в исходное состояние */
        logOut();
    }
}