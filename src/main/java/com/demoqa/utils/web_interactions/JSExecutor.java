package com.demoqa.utils.web_interactions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSExecutor {
    private static final Logger logger = LogManager.getLogger(JSExecutor.class);
    private final WaitActions wait;
    private final JavascriptExecutor jse;

    public JSExecutor(WebDriver driver) {
        this.jse = (JavascriptExecutor) driver;
        this.wait = new WaitActions(driver);
    }

    protected void clickJS(WebElement element) {
        try {
            jse.executeScript("arguments[0].click();", wait.elementToBeClickable(element));
        } catch (JavascriptException e) {
            logger.error("it is not possible click", e);
            throw e;
        }
    }

    protected void scrollToElement(WebElement element) {
        try {
            jse.executeScript("arguments[0].scrollIntoView();", wait.visible(element));
        } catch (JavascriptException e) {
            logger.error("it is not possible scroll to element", e);
            throw e;
        }
    }

    protected void scrollToTop() {
        try {
            jse.executeScript("window.scrollTo(0, 0);");
        } catch (JavascriptException e) {
            logger.error("it is not possible scroll to top", e);
            throw e;
        }
    }

    protected void scrollToBottom() {
        try {
            jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        } catch (JavascriptException e) {
            logger.error("it is not possible scroll to bottom", e);
            throw e;
        }
    }

    protected void clearInputValue(WebElement element) {
        try {
            jse.executeScript("arguments[0].value = '';", wait.visible(element));
        } catch (JavascriptException e) {
            logger.error("it is not possible clear input", e);
            throw e;
        }
    }

    protected void dismissAlert() {
        try {
            jse.executeScript("window.alert = function(){};");
        } catch (JavascriptException e) {
            logger.error("it is not possible dismiss alert", e);
            throw e;
        }
    }


    protected void doubleClick(WebElement element) {
        try {
            jse.executeScript("arguments[0].dispatchEvent(new MouseEvent('dblclick', {" +
                    "'view': window, " +
                    "'bubbles': true, " +
                    "'cancelable': true}))",
                    wait.elementToBeClickable(element));
        } catch (JavascriptException e) {
            logger.error("it is not possible double click", e);
            throw e;
        }
    }


    protected void contextClick(WebElement element) {
        try {
            jse.executeScript("arguments[0].dispatchEvent(new MouseEvent('contextmenu', {" +
                    "'view': window, " +
                    "'bubbles': true, " +
                    "'cancelable': true}))",
                    wait.elementToBeClickable(element));
        } catch (JavascriptException e) {
            logger.error("it is not possible context click", e);
            throw e;
        }
    }

    protected void sendKeys(WebElement element, String text) {
        try {
            jse.executeScript("arguments[0].value = '" + text + "';", wait.visible(element));
        } catch (JavascriptException e) {
            logger.error("it is not possible send keys", e);
            throw e;
        }
    }

    public void clearAndSendKeys (WebElement element, String text) {
        try {
            jse.executeScript("arguments[0].value = ''; arguments[0].value = '" + text + "';", wait.visible(element));
        } catch (JavascriptException e) {
            logger.error("it is not possible clear and send keys", e);
            throw e;
        }
    }
}
