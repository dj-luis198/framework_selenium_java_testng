package com.demoqa.utils.seleniumWebInteractions;

import com.demoqa.utils.externalFileUtility.PropertyUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitActions {
    private final Logger logger = LogManager.getLogger(WaitActions.class);
    private final WebDriverWait webDriverWait;

    public WaitActions(WebDriver driver) {
        PropertyUtility prop = new PropertyUtility();
        long wait_Duration = Long.parseLong(prop.initProperties("general").getProperty("wait.duration"));
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(wait_Duration));
    }

    public WebElement presenceOfElementLocated(By located){
        try {
            return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(located));
        } catch (TimeoutException | NoSuchElementException e) {
            logger.error("Timeout Exception or No Such Element Exception", e);
            throw new Error();
        }
    }

    public WebElement visible(WebElement element) {
        try {
            return webDriverWait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException | NoSuchElementException e) {
            logger.error("Timeout Exception or No Such Element Exception", e);
            throw new Error();
        }
    }

    public Boolean invisible(WebElement element) {
        try {
            return webDriverWait.until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException | NoSuchElementException e) {
            logger.error("Timeout Exception or No Such Element Exception", e);
            throw new Error();
        }
    }

    public WebElement elementToBeClickable(WebElement element){
        try {
            return webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException | NoSuchElementException e) {
            logger.error("Timeout Exception or No Such Element Exception", e);
            throw new Error();
        }
    }

    public Boolean elementToBeSelected(WebElement element){
        try {
            return webDriverWait.until(ExpectedConditions.elementToBeSelected(element));
        } catch (TimeoutException | NoSuchElementException e) {
            logger.error("Timeout Exception or No Such Element Exception", e);
            throw new Error();
        }
    }

    public Boolean textToBePresentInElement(WebElement element, String text){
        try {
            return webDriverWait.until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (TimeoutException | NoSuchElementException e) {
            logger.error("Timeout Exception or No Such Element Exception", e);
            throw new Error();
        }
    }

    public Boolean titleContains(String text){
        try {
            return webDriverWait.until(ExpectedConditions.titleContains(text));
        } catch (TimeoutException | NoSuchElementException e) {
            logger.error("Timeout Exception or No Such Element Exception", e);
            throw new Error();
        }
    }

    public Boolean elementSelectionStateToBe(WebElement element, Boolean state){
        try {
            return webDriverWait.until(ExpectedConditions.elementSelectionStateToBe(element, state));
        } catch (TimeoutException | NoSuchElementException e) {
            logger.error("Timeout Exception or No Such Element Exception", e);
            throw new Error();
        }
    }

    public Boolean urlToBe(String urlPage){
        try {
            return webDriverWait.until(ExpectedConditions.urlToBe(urlPage));
        } catch (TimeoutException | NoSuchElementException e) {
            logger.error("Timeout Exception or No Such Element Exception", e);
            throw new Error();
        }
    }

    public List<WebElement> presenceOfAllElementsLocatedBy(By locator){
        try {
            return webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (TimeoutException | NoSuchElementException e) {
            logger.error("Timeout Exception or No Such Element Exception", e);
            throw new Error();
        }
    }
}
