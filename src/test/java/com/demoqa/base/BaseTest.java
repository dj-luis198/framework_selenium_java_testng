package com.demoqa.base;

import com.demoqa.utils.browser.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {
    static BrowserFactory browserFactory = new BrowserFactory();
    @BeforeMethod
    @Parameters({"browser", "deviceName"})
    public void setUp(@Optional("chrome") String browser,
                       @Optional("Nest Hub Max") String deviceName) {
        WebDriver driver = browserFactory.getDriver(browser, deviceName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterMethod
    public void tearDown() {
        browserFactory.quitDriver();
    }

    public static WebDriver getDriver() {
        return browserFactory.getDriver();
    }
}
