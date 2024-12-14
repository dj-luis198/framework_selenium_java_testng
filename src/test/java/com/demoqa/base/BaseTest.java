package com.demoqa.base;

import com.demoqa.utils.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    @BeforeMethod
    @Parameters({"browser", "device"})
    public void setUp(@Optional("chrome") String browser,
                       @Optional("desktop") String device) throws MalformedURLException, URISyntaxException {
        driver = BrowserFactory.getDriver(browser, device);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
