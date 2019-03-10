package ru.beru;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class Login extends Account {
    @Test
    public void loginTest() {

        /* Переходим на сайт и закрываем рекламу */
        goToBeru();

        /* Вход в аккаунт */
        logIn();

        /* Проверяем сменилась ли надпись "Войти в аккаунт" на "Мой профиль" */
        Assert.assertTrue(driver.findElements(By.xpath("//span[text() = 'Мой профиль']")).size() > 0);

        /* Возвращаем систему в исходное состояние */
        logOut();
    }
}