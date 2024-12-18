package com.demoqa.utils.seleniumWebInteractions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SSendKeys {
    private final JSExecutor js;
    private final SActions actions;
    private final Waits wait;

    public SSendKeys(WebDriver driver) {
        this.js= new JSExecutor(driver);
        this.actions = new SActions(driver);
        this.wait = new Waits (driver);
    }

    protected void sendKeys (WebElement element, String text){
            actions.scrollToElement(element).sendKeys(text);
    }

    protected void clearAndSendKeys (WebElement element, String text){
        try {
            actions.scrollToElement(element).clear();
            element.sendKeys(text);
        } catch (Exception e) {
            try {
                js.scrollToElement(element);
                js.clearInputValue(element);
                js.clickJS(element);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
