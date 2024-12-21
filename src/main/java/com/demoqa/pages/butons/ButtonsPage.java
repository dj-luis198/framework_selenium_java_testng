package com.demoqa.pages.butons;

import com.demoqa.pages.ads.Ads;
import com.demoqa.utils.web_interactions.ElementActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ButtonsPage {
    private final ElementActions elementActions;

    @FindBy(id="doubleClickBtn")
    private WebElement doubleClickButton;
    @FindBy(id="rightClickBtn")
    private WebElement rightClickButton;
    @FindBy(id="dynamicClickMessage")
    private WebElement dynamicClickMessage;

    public ButtonsPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        elementActions = new ElementActions(driver);
        Ads ads = new Ads(driver);
        ads.removeAds();
    }

    public void clickDoubleClickButton(){
        elementActions.doubleClick(doubleClickButton);
    }

    public String getTitlePage(){
        return "DEMOQA";
    }
}
