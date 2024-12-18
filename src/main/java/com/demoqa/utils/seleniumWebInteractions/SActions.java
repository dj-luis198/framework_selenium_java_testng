package com.demoqa.utils.seleniumWebInteractions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SActions {
    private final Actions actions;
    private final Waits wait;
    private final JSExecutor js;

    public SActions(WebDriver driver) {
        this.actions = new Actions(driver);
        this.wait = new Waits(driver);
        this.js = new JSExecutor(driver);
    }

    public WebElement scrollToElement(WebElement element){
        try {
            actions.scrollToElement(wait.visible(element)).perform();
            return element;
        } catch (Exception e) {
            try {
                js.scrollToElement(element);
                return element;
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void doubleClick(WebElement element){
        try {
            actions.doubleClick(scrollToElement(element)).perform();
        } catch (Exception e) {
            try {
                js.scrollToElement(element);
                js.doubleClick(element);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void click(WebElement element){
        try {
            actions.click(scrollToElement(element)).perform();
        } catch (Exception e) {
            try {
                js.scrollToElement(element);
                js.clickJS(element);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void contextClick(WebElement element){
        try {
            actions.contextClick(scrollToElement(element)).perform();
        } catch (Exception e) {
            try {
                js.scrollToElement(element);
                js.contextClick(scrollToElement(element));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void moveToElement(WebElement element){
        try {
            actions.moveToElement(scrollToElement(element)).perform();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
