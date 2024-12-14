package com.demoqa.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

public class BrowserFactory {


    public static WebDriver getDriver(String browser, String device) throws URISyntaxException, MalformedURLException {
         WebDriver driver = null;
         String hubURL = "http://localhost:4444/wd/hub";
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (device.equalsIgnoreCase("mobile")) {
                options.addArguments("--window-size=375,667");
                options.setExperimentalOption("mobileEmulation", Map.of("deviceName", "Pixel 2"));
            } else
                if (device.equalsIgnoreCase("tablet")) {
                    options.addArguments("--window-size=768,1024");
                    options.setExperimentalOption("mobileEmulation", Map.of("deviceName", "iPad"));
                } else
                    if (device.equalsIgnoreCase("desktop")) {
                        options.addArguments("--window-size=1920,1080");
                    }
                    driver = new RemoteWebDriver(new URL(hubURL), options);
        } else
            if (browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                if (device.equalsIgnoreCase("mobile")) {
                    options.addPreference("general.useragent.override", "Mozilla/5.0 (Linux; Android 8.0.0; Pixel 2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Mobile Safari/537.36");
                    driver = new RemoteWebDriver(new URL(hubURL), options);
                    driver.manage().window().setSize(new Dimension(375, 667));
                } else if (device.equalsIgnoreCase("tablet")) {
                    options.addPreference("general.useragent.override", "Mozilla/5.0 (iPad; CPU OS 10_3 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) Version/10.0 Mobile/14E5239e Safari/602.1");
                    driver = new RemoteWebDriver(new URL(hubURL), options);
                    driver.manage().window().setSize(new Dimension(768, 1024));
                } else if (device.equalsIgnoreCase("desktop")) {
                    driver = new RemoteWebDriver(new URL(hubURL), options);
                    driver.manage().window().setSize(new Dimension(1920, 1080)); }
            } else
                if (browser.equalsIgnoreCase("edge")) {
                    driver = new EdgeDriver();
                }
                return driver;
    }
}
