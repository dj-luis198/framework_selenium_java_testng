package com.demoqa.utils.browser;

import com.demoqa.utils.emulatorDeviceWeb.Device;
import com.demoqa.utils.emulatorDeviceWeb.JsonReader;
import com.demoqa.utils.externalFileUtility.PropertyUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


public class BrowserFactory {

    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(BrowserFactory.class);

    private static final PropertyUtility prop = new PropertyUtility();
    private static final String HUB_URL = prop.initProperties("selenium_grid").getProperty("hub.url");
    private static final String DEVICES_FILE = prop.initProperties("paths").getProperty("devices.file");
    private static final String RUN_LOCAL = prop.initProperties("general").getProperty("run.local");

    public WebDriver getDriver() {
        return driver.get();
    }

    public void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public void quitDriver() {
        try {
            WebDriver webDriver = driver.get();
            if (webDriver != null) {
                webDriver.quit();
            }
        } finally {
            driver.remove();
        }
    }

    public WebDriver getDriver(String browser, String deviceName) {
        try {
            Device device = getDevice(deviceName);
            if (RUN_LOCAL != null && RUN_LOCAL.equals("true")) {
                return createLocalDriver(browser, device);
            } else {
                return createRemoteDriver(browser, device);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Device getDevice(String deviceName) {
        JsonReader jsonReader = new JsonReader(DEVICES_FILE);
        return jsonReader.getDevice(deviceName);
    }

    private WebDriver createRemoteDriver(String browser, Device device) throws MalformedURLException {
        switch (browser) {
            case "chrome":
                setDriver(createChromeDriver(device));
                break;
            case "firefox":
                setDriver(createFirefoxDriver(device));
                configureDimensions(getDriver(), device);
                break;
            default:
                throw new UnsupportedOperationException("Browser " + browser + " is not supported");
        }
        return getDriver();
    }

    private WebDriver createLocalDriver(String browser, Device device) {
        switch (browser) {
            case "chrome":
                ChromeOptions ChromeOptions = new ChromeOptions();
                configureChromeOptions(ChromeOptions, device);
                setDriver(new ChromeDriver(ChromeOptions));
                break;
            case "firefox":
                FirefoxOptions fireOptions = new FirefoxOptions();
                configureFirefoxOptions(fireOptions,device);
                setDriver(new FirefoxDriver(fireOptions));
                configureDimensions(getDriver(), device);
                break;
            default:
                throw new UnsupportedOperationException("Browser " + browser + " is not supported");
        }
        return getDriver();
    }

    private WebDriver createChromeDriver(Device device) throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        configureChromeOptions(options, device);
        return new RemoteWebDriver(new URL(HUB_URL), options);
    }

    private WebDriver createFirefoxDriver(Device device) throws MalformedURLException {
        FirefoxOptions options = new FirefoxOptions();
        configureFirefoxOptions(options, device);
        return new RemoteWebDriver(new URL(HUB_URL), options);
    }

    private void configureChromeOptions(ChromeOptions options, Device device) {
        options.setExperimentalOption("mobileEmulation", Map.of("deviceName", device.getName()));
        options.addArguments("--window-size=" + device.getWidth() + "," + device.getHeight());
        options.addArguments("--force-device-scale-factor=0.75");
    }

    private void configureFirefoxOptions(FirefoxOptions options, Device device) {
        options.addPreference("general.useragent.override", device.getFirefoxUserAgent());
        options.addPreference("layout.css.devPixelsPerPx", "0.75");
    }

    private void configureDimensions( WebDriver driver, Device device) {
        driver.manage().window().setSize(new Dimension(device.getWidth(), device.getHeight()));
    }
}