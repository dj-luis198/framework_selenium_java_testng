package com.demoqa.utils.browser;

import com.demoqa.utils.emulatorDeviceWeb.Device;
import com.demoqa.utils.emulatorDeviceWeb.JsonReader;
import com.demoqa.utils.externalFileUtility.PropertyUtility;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class BrowserFactory {
    private static final PropertyUtility prop = new PropertyUtility();
    private static final String HUB_URL = prop.initProperties("selenium_grid").getProperty("hub.url");
    private static final String DEVICES_FILE = prop.initProperties("browser_factory").getProperty("devices.file");

    public static WebDriver getDriver(String browser, String deviceName) {
        WebDriver driver;
        try {
            Device device = getDevice(deviceName);
            driver = createDriver(browser, device);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return driver;
    }

    private static Device getDevice(String deviceName) {
        JsonReader jsonReader = new JsonReader(DEVICES_FILE);
        return jsonReader.getDevice(deviceName);
    }

    private static WebDriver createDriver(String browser, Device device) throws MalformedURLException {
        WebDriver driver;
        switch (browser) {
            case "chrome":
                driver = createChromeDriver(device);
                break;
            case "firefox":
                driver = createFirefoxDriver(device);
                configureFirefoxDimensions(driver, device);
                break;
            default:
                throw new UnsupportedOperationException("Browser " + browser + " is not supported");
        }
        return driver;
    }

    private static WebDriver createChromeDriver(Device device) throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        configureChromeOptions(options, device);
        return new RemoteWebDriver(new URL(HUB_URL), options);
    }

    private static WebDriver createFirefoxDriver(Device device) throws MalformedURLException {
        FirefoxOptions options = new FirefoxOptions();
        configureFirefoxOptions(options, device);
        return new RemoteWebDriver(new URL(HUB_URL), options);
    }

    private static void configureChromeOptions(ChromeOptions options, Device device) {
                options.setExperimentalOption("mobileEmulation", Map.of("deviceName", device.getName()));
                options.addArguments("--window-size=" + device.getWidth() + "," + device.getHeight());
    }

    private static void configureFirefoxOptions(FirefoxOptions options, Device device) {
        options.addPreference("general.useragent.override", device.getFirefoxUserAgent());
    }

    private static void configureFirefoxDimensions( WebDriver driver, Device device) {
                driver.manage().window().setSize(new Dimension(device.getWidth(), device.getHeight()));
    }
}