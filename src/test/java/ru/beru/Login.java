package ru.beru;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class Login extends Settings {
    @Test
    public void loginTest() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        /* Авторизация на сайте */
        homePage.clickAccountButton();
        loginPage.enterLogin();
        loginPage.clickPasswordButton();
        loginPage.enterPassword();
        loginPage.signIn();

        /* Проверяем сменилась ли надпись "Войти в аккаунт" на "Мой профиль" */
        homePage.checkAccountText();
    }
}