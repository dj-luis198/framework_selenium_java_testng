package com.demoqa.pages.butons;

import com.demoqa.base.BasePages;
import org.openqa.selenium.support.PageFactory;

public class ButtonsPage extends BasePages {

    public ButtonsPage() {
        PageFactory.initElements(driver,this);
    }

    public String getTitlePage(){
        return "DEMOQA";
    }
}
