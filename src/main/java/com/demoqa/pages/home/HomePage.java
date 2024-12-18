package com.demoqa.pages.home;

import com.demoqa.base.BasePages;
import com.demoqa.pages.butons.ButtonsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;

public class HomePage extends BasePages {
    private final WebDriver driver;
    @FindBy(xpath = "(//div[@class='card-body'])[1]")
    private WebElement cardElements;
    @FindBy(xpath = "(//span[normalize-space()='Buttons'])[1]")
    private WebElement menuListButtons;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    public void clickCardElements(){
        click.right(cardElements);
    }

    public ButtonsPage clickMenuListButtons(){
        click.right(menuListButtons);
        return new ButtonsPage(driver);
    }
}
