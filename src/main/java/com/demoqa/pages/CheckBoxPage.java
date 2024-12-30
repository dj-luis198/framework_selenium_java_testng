package com.demoqa.pages;

import com.demoqa.pages.ads.Ads;
import com.demoqa.utils.enums.Properties;
import com.demoqa.utils.java_utility.JavaUtility;
import com.demoqa.utils.web_interactions.ElementActions;
import com.demoqa.utils.web_interactions.WaitActions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxPage {
    ElementActions elementActions;
    WaitActions waitActions;

    @FindBy(xpath = "//button[@title='Expand all']")
    private WebElement expandAllButton;
    @FindBy(xpath = "//button[@title='Collapse all']")
    private WebElement collapseAllButton;
    @FindBy(xpath = "//input [@type='checkbox']")
    private List<WebElement> checkboxes;
    @FindBy(id = "result")
    private WebElement output;

    public CheckBoxPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        elementActions = new ElementActions(driver);
        waitActions = new WaitActions(driver);
        Ads ads = new Ads(driver);
        ads.removeAds();
    }

    public void clickExpandAllButton() {
        elementActions.click(expandAllButton);
    }

    public Boolean isExpanded() {
        return checkboxes.size() == 17;
    }

    public void clickCollapseAllButton() {
        elementActions.click(collapseAllButton);
    }

    public Boolean isCollapsed() {
        return checkboxes.size() == 1;
    }

    public void clickExpandButtons() {
        By locator = By.xpath("//*[contains(@class,'rct-node-collapsed')]//child::button");
        List<WebElement> buttons = waitActions.presenceOfAllElementsLocatedBy(locator);
        while (!buttons.isEmpty()) {
            for (WebElement button : buttons) {
                elementActions.click(button);
            }
            try {
                buttons = waitActions.presenceOfAllElementsLocatedBy(locator);
            } catch (TimeoutException e) {
                buttons = new ArrayList<>();
            }
        }
    }

    public void selectCheckBoxes(String checkBox) {
        String[] listCheckBoxes = JavaUtility.generateArray(checkBox);
        for (String check : listCheckBoxes) {
            By locator = By.xpath("//*[@for='tree-node-"+check+"']//child::span[@class='rct-checkbox']");
            elementActions.click(locator);
        }
    }

    public String getOutputCheckBoxes() {
        return JavaUtility.replaceBreaks(elementActions.getDomProperty(output, Properties.INNER_TEXT.toString()));
    }

    public boolean verifyCkeckedCheckboxes(String output) {
        String[] listCheckBox = JavaUtility.generateArray(output);
        for (String check : listCheckBox) {
            By locator = By.id("tree-node-"+check);
            if(!elementActions.getDomProperty(locator, Properties.CHECKED.toString()).equals("true")) {
                return false;
            }
        }
        return true;
    }
}
