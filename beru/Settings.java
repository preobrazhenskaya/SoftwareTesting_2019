package ru.beru;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Settings {
    public WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/yanochka/IdeaProjects/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void close() {
        //driver.quit();
    }

    public void goToBeru() {

        /* Переход на сайт beru.ru */
        driver.get("https://beru.ru");

        /* Закрытие рекламы */
        WebElement closeAds = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("_1ZYDKa22GJ")));
        closeAds.click();
    }
}
