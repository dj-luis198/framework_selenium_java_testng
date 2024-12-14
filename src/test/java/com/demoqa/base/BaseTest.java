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
    @Parameters({"browser", "userAgent","width", "height", "deviceScaleFactor", "mobile"})
    public void setUp(@Optional("chrome") String browser,
                       @Optional("Mozilla/5.0 (iPad; CPU OS 10_3 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) Version/10.0 Mobile/14E5239e Safari/602.1") String userAgent,
                       @Optional("1920") int width,
                       @Optional("1080") int height,
                       @Optional("100") double deviceScaleFactor,
                       @Optional("false") boolean mobile) throws MalformedURLException, URISyntaxException {
        driver = BrowserFactory.getDriver(browser, userAgent, width, height, deviceScaleFactor, mobile);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
