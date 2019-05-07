package ru.beru;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Settings {
    public static EventFiringWebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/yanochka/IdeaProjects/chromedriver");
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        EventListener el = new EventListener();
        driver.register(el);
        driver.get("https://beru.ru");
    }

    @AfterMethod
    public void close() {
        /* Если входили в аккаунт - выходим */
        HomePage homePage = new HomePage(driver);
        if (homePage.checkLoggedIn()) {
            homePage.openAccount();
            homePage.logOut();
        }
        driver.quit();
    }

    @DataProvider(name = "City")
    public static Object[][] cities() {
        return new Object[][] {{"Хвалынск"}, {"Москва"}, {"Санкт-Петербург"}};
    }
}
