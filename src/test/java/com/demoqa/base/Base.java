package com.demoqa.base;

import com.demoqa.utils.browser.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Base {
    @BeforeMethod
    @Parameters({"browser", "deviceName"})
    public void setUp(@Optional("chrome") String browser,
                       @Optional("Nest Hub Max") String deviceName) {
        WebDriver driver = BrowserFactory.getDriver(browser, deviceName);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        BrowserFactory.quitDriver();
    }

    public WebDriver getDriver() {
        return BrowserFactory.getDriver();
    }
}
