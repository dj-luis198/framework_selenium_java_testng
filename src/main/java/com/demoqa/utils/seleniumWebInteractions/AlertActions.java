package com.demoqa.utils.seleniumWebInteractions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class AlertActions {
    private final Logger logger = LogManager.getLogger(AlertActions.class);
    WebDriver driver;
    private final WaitActions wait;

    public AlertActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitActions(driver);
    }

    public String getAlertTextIfPresent() {
        try {
            Alert alert=wait.alertIsPresent();
            driver.switchTo().alert();
            return alert.getText();
        } catch (TimeoutException e) {
            logger.error("Timeout Exception or No Such alert Exception", e);
        }
        return null;
    }

    public String getAndAcceptAlert() {
        try {
            Alert alert=wait.alertIsPresent();
            driver.switchTo().alert();
            String text = alert.getText();
            alert.accept();
            return text;
        } catch (TimeoutException e) {
            logger.error("Timeout Exception or No Such alert Exception", e);
        }
        return null;
    }

    public void acceptAlert() {
        try {
            Alert alert=wait.alertIsPresent();
            driver.switchTo().alert();
            alert.accept();
        } catch (TimeoutException e) {
            logger.error("Timeout Exception or No Such alert Exception", e);
        }
    }

    public void cancelAlert() {
        try {
            Alert alert=wait.alertIsPresent();
            driver.switchTo().alert();
            alert.dismiss();
        } catch (TimeoutException e) {
            logger.error("Timeout Exception or No Such alert Exception", e);
        }
    }

    public void enterTextInPromptAlert(String text) {
        try {
            Alert alert=wait.alertIsPresent();
            driver.switchTo().alert();
            alert.sendKeys(text);
            alert.accept();
        } catch (TimeoutException e) {
            logger.error("Timeout Exception or No Such alert Exception", e);
        }
    }
}
