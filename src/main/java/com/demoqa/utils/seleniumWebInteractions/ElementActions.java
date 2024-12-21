package com.demoqa.utils.seleniumWebInteractions;

import com.demoqa.utils.enums.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

//Métodos para interactuar con elementos de la página:
//click(WebElement element)
//doubleClick(WebElement element)
//sendKeys(WebElement element, String text)
//clear(WebElement element)
//isSelected(WebElement element)
//isEnabled(WebElement element)
//isVisible(WebElement element)
//isDisplayed(WebElement element)
//getCssValue(WebElement element, String propertyName)
//getAttribute(WebElement element, String attributeName)
//getTagName(WebElement element)
//getText(WebElement element)
//getRect(WebElement element)
//getLocation(WebElement element)
//getSize(WebElement element)

public class ElementActions {
    private final JSExecutor js;
    private final Actions actions;
    private final WaitActions wait;

    public ElementActions(WebDriver driver) {
        this.js = new JSExecutor(driver);
        this.actions = new Actions(driver);
        this.wait = new WaitActions(driver);
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

    public void click (WebElement element){
        try {
            scrollToElement(element).click();
        } catch (Exception e) {
            try {
                js.scrollToElement(element);
                js.clickJS(element);
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

    public void clearAndSendKeys (WebElement element, String text){
        try {
            scrollToElement(element).clear();
            element.sendKeys(text);
        } catch (Exception e) {
            try {
                js.scrollToElement(element);
                js.clearInputValue(element);
                js.sendKeys(element, text);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void SendKeys (WebElement element, String text){
        try {
            scrollToElement(element).sendKeys(text);
        } catch (Exception e) {
            try {
                js.scrollToElement(element);
                js.sendKeys(element, text);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void getText (WebElement element){
        try {
            wait.visible(element).getText();
        } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    public String getDomProperty(WebElement element, String propertyName){
        return wait.visible(element).getDomProperty(propertyName);
    }

    public String getDomProperty(By locator, String propertyName){
        return wait.presenceOfElementLocated(locator).getDomProperty(propertyName);
    }
}
