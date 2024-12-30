package com.demoqa.pages;

import com.demoqa.pages.ads.Ads;
import com.demoqa.utils.enums.Properties;
import com.demoqa.utils.web_interactions.ElementActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TextBoxPage {
    private final ElementActions elementActions;

    @FindBy(id = "userName")
    private WebElement userName;
    @FindBy(id = "userEmail")
    private WebElement userEmail;
    @FindBy(id = "currentAddress")
    private WebElement userCurrentAddress;
    @FindBy(id = "permanentAddress")
    private WebElement userPermanentAddress;
    @FindBy(id = "output")
    private WebElement output;
    @FindBy(id = "submit")
    private WebElement submit;

    public TextBoxPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        elementActions = new ElementActions(driver);
        Ads ads = new Ads(driver);
        ads.removeAds();
    }

    public void fillForm(String name, String email, String currentAddress, String permanentAddress) {
        elementActions.clearAndSendKeys(userName, name);
        elementActions.clearAndSendKeys(userEmail, email);
        elementActions.clearAndSendKeys(userCurrentAddress, currentAddress);
        elementActions.clearAndSendKeys(userPermanentAddress, permanentAddress);
        elementActions.click(submit);
    }

    public String getOutput() {
        return elementActions.getDomProperty(output, Properties.OUTER_TEXT.toString());
    }
}
