package com.demoqa.pages.home;

import com.demoqa.pages.ads.Ads;
import com.demoqa.pages.butons.ButtonsPage;
import com.demoqa.utils.seleniumWebInteractions.JSExecutor;
import com.demoqa.utils.seleniumWebInteractions.ElementActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private final WebDriver driver;
    private final ElementActions elementActions;
    private final JSExecutor js;

    @FindBy(xpath = "//h5[normalize-space()='Elements']")
    private WebElement cardElements;
    @FindBy(xpath = "//span[normalize-space()='Buttons']")
    private WebElement menuListButtons;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.elementActions = new ElementActions(driver);
        this.driver=driver;
        this.js = new JSExecutor(driver);
        Ads ads = new Ads(driver);
        ads.removeAds();
    }

    public void clickCardElements(){
        elementActions.click(cardElements);
    }

    public ButtonsPage clickMenuListButtons(){
        elementActions.click(menuListButtons);
        return new ButtonsPage(driver);
    }
}
