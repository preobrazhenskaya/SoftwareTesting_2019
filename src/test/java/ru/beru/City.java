package ru.beru;

import org.testng.annotations.Test;


public class City extends Settings {
    @Test(dataProvider = "City")
    public void cityTest (String cityName) {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        SettingsPage settingsPage = new SettingsPage(driver);
        ChangeCityPage changeCityPage = new ChangeCityPage(driver);

        /* Смена города */
        homePage.goToChangeCityPage();
        changeCityPage.enterCity(cityName);
        changeCityPage.clickMenuItem();
        changeCityPage.saveCity();
        homePage.checkCity(cityName);

        /* Вход в аккаунт */
        homePage.clickAccountButton();
        loginPage.enterLogin();
        loginPage.clickPasswordButton();
        loginPage.enterPassword();
        loginPage.signIn();

        /* Переход в настройки */
        homePage.openAccount();
        homePage.openSettings();

        /* Проверка совпадения городов */
        settingsPage.checkCityEquality();

        /* Возвращаем систему в исходное состояние */
        settingsPage.openAccount();
        settingsPage.logOut();
    }
}
