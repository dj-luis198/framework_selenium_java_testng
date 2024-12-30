package com.demoqa.utils.browser;

import com.demoqa.utils.emulator_device_web.Device;
import com.demoqa.utils.emulator_device_web.JsonReader;
import com.demoqa.utils.exceptions.FileException;
import com.demoqa.utils.external_file_utility.PropertyUtility;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

//ThreadLocal para que cada thread tenga su propio driver
//ThreadGuard para que aseguremos que el controlador solo sea llamado por el hilo que lo creo.

public class BrowserFactory {
    private BrowserFactory() {
    }

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static final PropertyUtility prop = new PropertyUtility();
    private static final String HUB_URL = prop.initProperties("selenium_grid").getProperty("hub.url");
    private static final String DEVICES_FILE = prop.initProperties("paths").getProperty("devices.file");
    private static final String RUN_LOCAL = prop.initProperties("general").getProperty("run.local");

    public static WebDriver getDriver() {
        return ThreadGuard.protect(driver.get());
    }

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static void quitDriver() {
        try {
            WebDriver webDriver = driver.get();
            if (webDriver != null) {
                webDriver.quit();
            }
        } finally {
            driver.remove();
        }
    }

    public static WebDriver getDriver(String browser, String deviceName) {
        try {
            Device device = getDevice(deviceName);
            if (RUN_LOCAL != null && RUN_LOCAL.equals("true")) {
                return createLocalDriver(browser, device);
            } else {
                return createRemoteDriver(browser, device);
            }
        } catch (Exception e) {
            throw new WebDriverException(e);
        }
    }

    private static Device getDevice(String deviceName) throws FileException {
        JsonReader jsonReader = new JsonReader(DEVICES_FILE);
        return jsonReader.getDevice(deviceName);
    }

    private static WebDriver createRemoteDriver(String browser, Device device) throws MalformedURLException, URISyntaxException {
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

    private static WebDriver createLocalDriver(String browser, Device device) {
        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                configureChromeOptions(chromeOptions, device);
                setDriver(ThreadGuard.protect(new ChromeDriver(chromeOptions)));
                break;
            case "firefox":
                FirefoxOptions fireOptions = new FirefoxOptions();
                configureFirefoxOptions(fireOptions, device);
                setDriver(ThreadGuard.protect(new FirefoxDriver(fireOptions)));
                configureDimensions(getDriver(), device);
                break;
            default:
                throw new UnsupportedOperationException("Browser " + browser + " is not supported");
        }
        return getDriver();
    }

    private static WebDriver createChromeDriver(Device device) throws MalformedURLException, URISyntaxException {
        ChromeOptions options = new ChromeOptions();
        configureChromeOptions(options, device);
        return new RemoteWebDriver(new URI(HUB_URL).toURL(), options);
    }

    private static WebDriver createFirefoxDriver(Device device) throws MalformedURLException, URISyntaxException {
        FirefoxOptions options = new FirefoxOptions();
        configureFirefoxOptions(options, device);
        return new RemoteWebDriver(new URI(HUB_URL).toURL(), options);
    }

    private static void configureChromeOptions(ChromeOptions options, Device device) {
        options.setExperimentalOption("mobileEmulation", Map.of("deviceName", device.getName()));
        options.addArguments("--window-size=" + device.getWidth() + "," + device.getHeight());
        options.addArguments("--force-device-scale-factor=0.75");
    }

    private static void configureFirefoxOptions(FirefoxOptions options, Device device) {
        options.addPreference("general.useragent.override", device.getFirefoxUserAgent());
        options.addPreference("layout.css.devPixelsPerPx", "0.75");

    }

    private static void configureDimensions(WebDriver driver, Device device) {
        driver.manage().window().setSize(new Dimension(device.getWidth(), device.getHeight()));
    }
}