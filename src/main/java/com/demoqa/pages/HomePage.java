package com.demoqa.pages;

import com.demoqa.pages.ads.Ads;
import com.demoqa.utils.web_interactions.ElementActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private final WebDriver driver;
    private final ElementActions elementActions;

    @FindBy(xpath = "//h5[normalize-space()='Elements']")
    private WebElement cardElements;
    @FindBy(xpath = "//span[normalize-space()='Buttons']")
    private WebElement menuListButtons;
    @FindBy(xpath = "//span[normalize-space()='Text Box']")
    private WebElement menuListTextBox;
    @FindBy(xpath = "//span[normalize-space()='Radio Button']")
    private WebElement menuListRadioButton;
    @FindBy(xpath = "//span[normalize-space()='Check Box']")
    private WebElement menuListCheckBox;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.elementActions = new ElementActions(driver);
        this.driver = driver;
        Ads ads = new Ads(driver);
        ads.removeAds();
    }

    public void clickCardElements() {
        elementActions.click(cardElements);
    }

    public ButtonsPage clickMenuListButtons() {
        elementActions.click(menuListButtons);
        return new ButtonsPage(driver);
    }

    public TextBoxPage clickMenuListTextBox() {
        elementActions.click(menuListTextBox);
        return new TextBoxPage(driver);
    }

    public RadioButtonPage clickMenuListRadioButton() {
        elementActions.click(menuListRadioButton);
        return new RadioButtonPage(driver);
    }

    public CheckBoxPage clickMenuListCheckBox() {
        elementActions.click(menuListCheckBox);
        return new CheckBoxPage(driver);
    }
}
