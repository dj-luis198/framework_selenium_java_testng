package com.demoqa.pages.butons;

import com.demoqa.base.BasePages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ButtonsPage extends BasePages {

    public ButtonsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public String getTitlePage(){
        return "DEMOQA";
    }
}
