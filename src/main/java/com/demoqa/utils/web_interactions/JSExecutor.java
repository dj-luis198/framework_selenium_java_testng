package com.demoqa.utils.web_interactions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSExecutor {
    private final WaitActions wait;
    private final JavascriptExecutor jse;

    public JSExecutor(WebDriver driver) {
        this.jse = (JavascriptExecutor) driver;
        this.wait = new WaitActions(driver);
    }

    protected void clickJS(WebElement element) {
        jse.executeScript("arguments[0].click();", wait.elementToBeClickable(element));
    }

    protected void scrollToElement(WebElement element) {
        jse.executeScript("arguments[0].scrollIntoView();", wait.visible(element));
    }

    protected void scrollToTop() {
        jse.executeScript("window.scrollTo(0, 0);");
    }

    protected void scrollToBottom() {
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    protected void clearInputValue(WebElement element) {
        jse.executeScript("arguments[0].value = '';", wait.visible(element));
    }

    protected void dismissAlert() {
        jse.executeScript("window.alert = function(){};");
    }


    protected void doubleClick(WebElement element) {
        jse.executeScript("arguments[0].dispatchEvent(new MouseEvent('dblclick', {" +
                "'view': window, " +
                "'bubbles': true, " +
                "'cancelable': true}))",
                wait.elementToBeClickable(element));
    }


    protected void contextClick(WebElement element) {
        jse.executeScript("arguments[0].dispatchEvent(new MouseEvent('contextmenu', {" +
                "'view': window, " +
                "'bubbles': true, " +
                "'cancelable': true}))",
                wait.elementToBeClickable(element));
    }

    protected void sendKeys(WebElement element, String text) {
        jse.executeScript("arguments[0].value = '" + text + "';", wait.visible(element));
    }
}
