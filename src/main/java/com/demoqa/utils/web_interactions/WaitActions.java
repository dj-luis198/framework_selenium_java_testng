package com.demoqa.utils.web_interactions;

import com.demoqa.utils.exceptions.ElementException;
import com.demoqa.utils.external_file_utility.PropertyUtility;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitActions {
    private static final String ERRORMESSAGE = "Timeout Exception or No Such Element Exception";
    private final WebDriverWait webDriverWait;

    public WaitActions(WebDriver driver) {
        PropertyUtility prop = new PropertyUtility();
        long waitDuration = Long.parseLong(prop.initProperties("general").getProperty("wait.duration"));
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(waitDuration));
    }

    public WebElement presenceOfElementLocated(By located) {
        try {
            return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(located));
        } catch (TimeoutException | NoSuchElementException e) {
            throw new ElementException(ERRORMESSAGE,e);
        }
    }

    public WebElement visible(WebElement element) {
        try {
            return webDriverWait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException | NoSuchElementException e) {
            throw new ElementException(ERRORMESSAGE,e);
        }
    }

    public Boolean invisible(WebElement element) {
        try {
            return webDriverWait.until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException | NoSuchElementException e) {
            throw new ElementException(ERRORMESSAGE,e);
        }
    }

    public WebElement elementToBeClickable(WebElement element){
        try {
            return webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException | NoSuchElementException e) {
            throw new ElementException(ERRORMESSAGE,e);
        }
    }

    public Boolean elementToBeSelected(WebElement element){
        try {
            return webDriverWait.until(ExpectedConditions.elementToBeSelected(element));
        } catch (TimeoutException | NoSuchElementException e) {
            throw new ElementException(ERRORMESSAGE,e);
        }
    }

    public Boolean textToBePresentInElement(WebElement element, String text){
        try {
            return webDriverWait.until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (TimeoutException | NoSuchElementException e) {
            throw new ElementException(ERRORMESSAGE,e);
        }
    }

    public Boolean titleContains(String text){
        try {
            return webDriverWait.until(ExpectedConditions.titleContains(text));
        } catch (TimeoutException | NoSuchElementException e) {
            throw new ElementException(ERRORMESSAGE,e);
        }
    }

    public Boolean elementSelectionStateToBe(WebElement element, Boolean state){
        try {
            return webDriverWait.until(ExpectedConditions.elementSelectionStateToBe(element, state));
        } catch (TimeoutException | NoSuchElementException e) {
            throw new ElementException(ERRORMESSAGE,e);
        }
    }

    public Boolean urlToBe(String urlPage){
        try {
            return webDriverWait.until(ExpectedConditions.urlToBe(urlPage));
        } catch (TimeoutException | NoSuchElementException e) {
            throw new ElementException(ERRORMESSAGE,e);
        }
    }

    public List<WebElement> presenceOfAllElementsLocatedBy(By locator){
        try {
            return webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (TimeoutException | NoSuchElementException e) {
            throw new ElementException(ERRORMESSAGE,e);
        }
    }

    public void numberOfWindowsToBe() {
        try {
            webDriverWait.until(ExpectedConditions.numberOfWindowsToBe(2));
        } catch (TimeoutException | NoSuchWindowException e) {
            throw new ElementException("Timeout Exception or window exception",e);
        }
    }

    public Alert alertIsPresent() {
        try {
            return webDriverWait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            throw new ElementException("Alert no present",e);
        }
    }
}
