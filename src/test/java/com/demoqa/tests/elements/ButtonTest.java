package com.demoqa.tests.elements;

import com.demoqa.base.Base;
import com.demoqa.pages.ButtonsPage;
import com.demoqa.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.*;

public class ButtonTest extends Base {

    @Test
    public void doubleClickTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickCardElements();
        ButtonsPage buttonPage = homePage.clickMenuListButtons();
        buttonPage.clickDoubleClickButton();
        Assert.assertEquals(buttonPage.getMessageDoubleClick(), "You have done a double click");
    }

    @Test
    public void clickTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickCardElements();
        ButtonsPage buttonPage = homePage.clickMenuListButtons();
        buttonPage.clickButton();
        Assert.assertEquals(buttonPage.getMessageDynamicClick(), "You have done a dynamic click");
    }

    @Test
    public void rightClickTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickCardElements();
        ButtonsPage buttonPage = homePage.clickMenuListButtons();
        buttonPage.rightClickButton();
        Assert.assertEquals(buttonPage.getMessageRightClick(), "You have done a right click");
    }
}
