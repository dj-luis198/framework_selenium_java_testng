package com.demoqa.utils.seleniumWebInteractions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SClick {
    private final JSExecutor js;
    private final SActions actions;
    private final Waits wait;

    public SClick(WebDriver driver) {
        this.js = new JSExecutor(driver);
        this.actions = new SActions(driver);
        this.wait = new Waits (driver);
    }

    public void right (WebElement element){
        try {
            actions.scrollToElement(element).click();
        } catch (Exception e) {
            try {
                js.scrollToElement(element);
                js.clickJS(element);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
