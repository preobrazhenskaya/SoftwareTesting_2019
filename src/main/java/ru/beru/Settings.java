package ru.beru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Settings extends HighlightElement {
    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/yanochka/IdeaProjects/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

    public void goToBeru() {

        /* Переход на сайт beru.ru */
        driver.get("https://beru.ru");

        /* Закрытие рекламы */
        WebElement closeAds = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("_1ZYDKa22GJ")));
        highlighter(driver, closeAds);
        closeAds.click();
    }

    @DataProvider(name = "City")
    public static Object[] cities() {
        return new Object[] {"Хвалынск"};
    }
}
