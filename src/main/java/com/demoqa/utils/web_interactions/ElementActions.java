package com.demoqa.utils.web_interactions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class ElementActions {
    private static final Logger logger = LogManager.getLogger(ElementActions.class);
    private final JSExecutor js;
    private final Actions actions;
    private final WaitActions wait;

    public ElementActions(WebDriver driver) {
        this.js = new JSExecutor(driver);
        this.actions = new Actions(driver);
        this.wait = new WaitActions(driver);
    }

    public WebElement scrollToElement(WebElement element) {
        try {
            actions.scrollToElement(wait.visible(element)).perform();
            return element;
        } catch (Exception e) {
            logger.info("retrying scrollToElement with js");
            try {
                js.scrollToElement(element);
                return element;
            } catch (Exception ex) {
                logger.error("it is not possible scroll to element", ex);
                throw ex;
            }
        }
    }

    public void click(WebElement element) {
        try {
            wait.elementToBeClickable(element).click();
        } catch (ElementNotInteractableException e) {
            logger.info("retrying click with js");
            try {
                js.clickJS(element);
            } catch (ElementNotInteractableException | JavascriptException ex) {
                logger.error("it is not possible click", ex);
                throw ex;
            }
        }
    }

    public void click(By locator) {
        try {
            wait.presenceOfElementLocated(locator).click();
        } catch (ElementNotInteractableException e) {
            logger.info("retrying click with js");
            try {
                js.clickJS(wait.presenceOfElementLocated(locator));
            } catch (ElementNotInteractableException | JavascriptException ex) {
                logger.error("it is not possible click", ex);
                throw ex;
            }
        }
    }

    public void doubleClick(WebElement element) {
        try {
            actions.doubleClick(wait.elementToBeClickable(element)).perform();
        } catch (Exception e) {
            logger.info("retrying doubleClick with js");
            try {
                js.doubleClick(element);
            } catch (Exception ex) {
                logger.error("it is not possible double click", ex);
                throw ex;
            }
        }
    }

    public void contextClick(WebElement element) {
        try {
            js.contextClick(element);
        } catch (Exception ex) {
            logger.error("it is not possible context click", ex);
            throw ex;
        }
    }

    public void moveToElement(WebElement element) {
        try {
            actions.moveToElement(wait.visible(element)).perform();
        } catch (Exception e) {
            logger.error("it is not possible to move to element", e);
            throw e;
        }
    }

    public void clearAndSendKeys(WebElement element, String text) {
        try {
            wait.visible(element).clear();
            element.sendKeys(text);
        } catch (TimeoutException e) {
            logger.info("retrying clearAndSendKeys with js");
            try {
                js.clearAndSendKeys(element, text);
            } catch (Exception ex) {
                logger.error("it is not possible clear and send keys", ex);
                throw ex;
            }
        }
    }

    public void sendKeys(WebElement element, String text) {
        try {
            wait.visible(element).sendKeys(text);
        } catch (Exception e) {
            logger.info("retrying sendKeys with js");
            try {
                js.sendKeys(element, text);
            } catch (Exception ex) {
                logger.error("it is not possible send keys", ex);
                throw ex;
            }
        }
    }

    public String getText(WebElement element) {
        return wait.visible(element).getText();
    }

    public String getDomProperty(WebElement element, String propertyName) {
        return element.getDomProperty(propertyName);
    }

    public String getDomProperty(By locator, String propertyName) {
        return wait.presenceOfElementLocated(locator).getDomProperty(propertyName);
    }

    public String getDomAtribute(WebElement element, String propertyName) {
        return element.getDomAttribute(propertyName);
    }
}
