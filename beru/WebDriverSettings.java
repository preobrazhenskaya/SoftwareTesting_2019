package ru.beru;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSettings {
    public WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/yanochka/IdeaProjects/chromedriver");
        driver = new ChromeDriver();
    }

    @After
    public void close() {
        driver.quit();
    }
}
