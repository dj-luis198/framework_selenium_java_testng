package com.demoqa.utils.seleniumWebInteractions;

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
        wait.elementToBeClickable(element);
        jse.executeScript("arguments[0].click();", element);
    }

    protected void scrollToElement(WebElement element) {
        wait.visible(element);
        jse.executeScript("arguments[0].scrollIntoView();", element);
    }

    protected void scrollToTop() {
        jse.executeScript("window.scrollTo(0, 0);");
    }

    protected void scrollToBottom() {
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    protected void clearInputValue(WebElement element) {
        wait.visible(element);
        jse.executeScript("arguments[0].value = '';", element);
    }

    protected void dismissAlert() {
        jse.executeScript("window.alert = function(){};");
    }


    protected void doubleClick(WebElement element) {
        wait.elementToBeClickable(element);
        jse.executeScript("arguments[0].dispatchEvent(new MouseEvent('dblclick', {'view': window, 'bubbles': true, 'cancelable': true}))", element);
    }


    protected void contextClick(WebElement element) {
        wait.elementToBeClickable(element);
        jse.executeScript("arguments[0].dispatchEvent(new MouseEvent('contextmenu', {'view': window, 'bubbles': true, 'cancelable': true}))", element);
    }

    protected void sendKeys(WebElement element, String text) {
        wait.elementToBeClickable(element);
        jse.executeScript("arguments[0].value = '" + text + "';", element);
    }
}
