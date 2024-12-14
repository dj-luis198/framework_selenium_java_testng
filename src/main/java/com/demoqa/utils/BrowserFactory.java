package com.demoqa.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v129.emulation.Emulation;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BrowserFactory {

    public static WebDriver getDriver(String browser, String userAgent,int width, int height, double deviceScaleFactor, boolean mobile) throws URISyntaxException, MalformedURLException {
         WebDriver driver = null;
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> pref = new HashMap<String, Object>();
            //options.addArguments("--headless=new");
            options.addArguments("--start-fullscreen");
            options.setExperimentalOption("prefs", pref);
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

            driver =new ChromeDriver(options);
            DevTools devTools = ((ChromeDriver) driver).getDevTools();
            devTools.createSession();
            devTools.send(Emulation.setDeviceMetricsOverride(
                    width,
                    height,
                    deviceScaleFactor,
                    mobile,
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty()));
        } else
            if (browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("general.useragent.override", userAgent);
                options.setProfile(profile);
                driver = new FirefoxDriver(options);
                driver.manage().window().setSize(new Dimension(375, 667));
                //driver.manage().window().fullscreen();
            } else
                if (browser.equalsIgnoreCase("edge")) {
                    driver = new EdgeDriver();
                }
                return driver;
    }
}
