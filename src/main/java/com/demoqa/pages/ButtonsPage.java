package com.demoqa.pages;

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
    @FindBy(xpath = "(//button[normalize-space()='Click Me'])[1]")
    private WebElement clickButton;
    @FindBy(id="dynamicClickMessage")
    private WebElement dynamicClickMessage;
    @FindBy(id="doubleClickMessage")
    private WebElement doubleClickMessage;
    @FindBy(id="rightClickMessage")
    private WebElement rightClickMessage;

    public ButtonsPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        elementActions = new ElementActions(driver);
        Ads ads = new Ads(driver);
        ads.removeAds();
    }

    public void clickDoubleClickButton(){
        elementActions.doubleClick(doubleClickButton);
    }

    public void clickButton(){
        elementActions.click(clickButton);
    }

    public void rightClickButton(){
        elementActions.contextClick(rightClickButton);
    }

    public String getMessageDynamicClick(){
        return elementActions.getText(dynamicClickMessage);
    }

    public String getMessageDoubleClick(){
        return elementActions.getText(doubleClickMessage);
    }

    public String getMessageRightClick(){
        return elementActions.getText(rightClickMessage);
    }
}
