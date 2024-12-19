package com.demoqa.pages.home;

import com.demoqa.base.BasePages;
import com.demoqa.pages.butons.ButtonsPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePages {

    @FindBy(xpath = "(//div[@class='card-body'])[1]")
    private WebElement cardElements;
    @FindBy(xpath = "(//span[normalize-space()='Buttons'])[1]")
    private WebElement menuListButtons;

    public HomePage() {
        PageFactory.initElements(driver,this);
    }

    public void clickCardElements(){
        click.right(cardElements);
    }

    public ButtonsPage clickMenuListButtons(){
        click.right(menuListButtons);
        return new ButtonsPage();
    }
}
