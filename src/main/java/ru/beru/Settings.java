package ru.beru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Settings {
    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/yanochka/IdeaProjects/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://beru.ru");
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

    @DataProvider(name = "City")
    public static Object[] cities() {
        return new Object[] {"Хвалынск"};
    }
}
