package com.demoqa.pages.ads;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Ads {
    private final WebDriver driver;

    public Ads(WebDriver driver) {
        this.driver = driver;
    }

    public void removeAds() {
        List<WebElement> ads = driver.findElements(By.cssSelector(".GoogleCreativeContainerClass, [id^='Ad.Plus'], .cursor-pointer"));
        for (WebElement ad : ads) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].remove()", ad);
        }
    }
}
