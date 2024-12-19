package com.demoqa.base;

import com.demoqa.utils.browser.BrowserFactory;
import com.demoqa.utils.seleniumWebInteractions.JSExecutor;
import com.demoqa.utils.seleniumWebInteractions.SActions;
import com.demoqa.utils.seleniumWebInteractions.SClick;
import org.openqa.selenium.WebDriver;

public class BasePages {
    protected WebDriver driver = BrowserFactory.getDriver();
    protected SClick click= new SClick(driver);
    protected SActions actions= new SActions(driver);
    protected JSExecutor js= new JSExecutor(driver);
}
