package com.demoqa.pages;

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
    @FindBy(id = "submit")
    private WebElement submit;
    @FindBy(id = "name")
    private WebElement outputName;
    @FindBy(id = "email")
    private WebElement outputEmail;
    @FindBy(xpath = "//p[@id='currentAddress']")
    private WebElement outputCurrentAddress;
    @FindBy(xpath = "//p[@id='permanentAddress']")
    private WebElement outputPermanentAddress;

    public TextBoxPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        elementActions = new ElementActions(driver);
    }

    public void fillForm(String name, String email, String currentAddress, String permanentAddress) {
        elementActions.clearAndSendKeys(userName, name);
        elementActions.clearAndSendKeys(userEmail, email);
        elementActions.clearAndSendKeys(userCurrentAddress, currentAddress);
        elementActions.clearAndSendKeys(userPermanentAddress, permanentAddress);
        elementActions.click(submit);
    }

    public String getOutputName() {
        return elementActions.getText(outputName);
    }

    public String getOutputEmail() {
        return elementActions.getText(outputEmail);
    }

    public String getOutputCurrentAddress() {
        return elementActions.getText(outputCurrentAddress);
    }

    public String getOutputPermanentAddress() {
        return elementActions.getText(outputPermanentAddress);
    }
}
