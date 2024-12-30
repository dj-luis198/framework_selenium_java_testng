package com.demoqa.pages;

import com.demoqa.pages.ads.Ads;
import com.demoqa.utils.enums.Properties;
import com.demoqa.utils.web_interactions.ElementActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RadioButtonPage {
    private final ElementActions elementActions;

    @FindBy(css = "label[for='yesRadio']")
    private WebElement yesRadio;
    @FindBy(css = "label[for='impressiveRadio']")
    private WebElement impressiveRadio;
    @FindBy(id = "noRadio")
    private WebElement noRadio;
    @FindBy(className = "text-success")
    private WebElement output;

    public RadioButtonPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        elementActions = new ElementActions(driver);
        Ads ads = new Ads(driver);
        ads.removeAds();
    }

    public void clickYesRadio() {
        elementActions.click(yesRadio);
    }

    public void clickImpressiveRadio() {
        elementActions.click(impressiveRadio);
    }

    public String getOutput() {
        return elementActions.getText(output);
    }

    public String isDisabled() {
        return elementActions.getDomProperty(noRadio, Properties.DISABLED.toString());
    }
}
