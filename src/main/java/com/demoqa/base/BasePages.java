package com.demoqa.base;

import com.demoqa.utils.seleniumWebInteractions.JSExecutor;
import com.demoqa.utils.seleniumWebInteractions.SActions;
import com.demoqa.utils.seleniumWebInteractions.SClick;
import org.openqa.selenium.WebDriver;

public class BasePages {
    protected SClick click;
    protected SActions actions;
    protected JSExecutor js;

    public BasePages(WebDriver driver) {
        click = new SClick(driver);
        actions = new SActions(driver);
        js = new JSExecutor(driver);
    }
}
