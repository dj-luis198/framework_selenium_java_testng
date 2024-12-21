package com.demoqa.utils.web_interactions;

import com.demoqa.utils.exceptions.ElementException;
import org.openqa.selenium.*;
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
    private static final String ERRORMESSAGECLICK = "it is not possible to click on the element";
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
                throw new ElementException(ERRORMESSAGECLICK,ex);
            }
        }
    }

    public void click (WebElement element){
        try {
            wait.elementToBeClickable(element).click();
        } catch (ElementNotInteractableException e) {
            try {
                js.clickJS(element);
            } catch (ElementNotInteractableException | JavascriptException ex) {
                throw new ElementException(ERRORMESSAGECLICK,ex);
            }
        }
    }

    public void doubleClick(WebElement element){
        try {
            actions.doubleClick(wait.elementToBeClickable(element)).perform();
        } catch (Exception e) {
            try {
                js.doubleClick(element);
            } catch (Exception ex) {
                throw new ElementException(ERRORMESSAGECLICK,ex);
            }
        }
    }

    public void contextClick(WebElement element){
            try {
                js.contextClick(element);
            } catch (Exception ex) {
                throw new ElementException(ERRORMESSAGECLICK,ex);
            }
    }

    public void moveToElement(WebElement element){
        try {
            actions.moveToElement(wait.visible(element)).perform();
        } catch (Exception e) {
            throw new ElementException("it is not possible to move to element",e);
        }
    }

    public void clearAndSendKeys (WebElement element, String text){
        try {
            wait.visible(element).clear();
            element.sendKeys(text);
        } catch (Exception e) {
            try {
                js.clearInputValue(element);
                js.sendKeys(element, text);
            } catch (Exception ex) {
                throw new ElementException("it is not possible clear and send keys",ex);
            }
        }
    }

    public void sendKeys (WebElement element, String text){
        try {
            wait.visible(element).sendKeys(text);
        } catch (Exception e) {
            try {
                js.sendKeys(element, text);
            } catch (Exception ex) {
                throw new ElementException("it is not possible send keys",ex);
            }
        }
    }

    public String getText (WebElement element){
            return wait.visible(element).getText();
    }

    public String getDomProperty(WebElement element, String propertyName){
        return wait.visible(element).getDomProperty(propertyName);
    }

    public String getDomProperty(By locator, String propertyName){
        return wait.presenceOfElementLocated(locator).getDomProperty(propertyName);
    }
}
