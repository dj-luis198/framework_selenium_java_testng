package com.demoqa.tests.elements;

import com.demoqa.base.Base;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.RadioButtonPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RadioButtonTest extends Base {

    @Test
    public void clickRadioButtonYesTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickCardElements();
        RadioButtonPage radioButtonPage = homePage.clickMenuListRadioButton();
        radioButtonPage.clickYesRadio();
        Assert.assertEquals(radioButtonPage.getOutput(), "Yes");
    }

    @Test
    public void clickRadioButtonImpressiveTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickCardElements();
        RadioButtonPage radioButtonPage = homePage.clickMenuListRadioButton();
        radioButtonPage.clickImpressiveRadio();
        Assert.assertEquals(radioButtonPage.getOutput(), "Impressive");
    }

    @Test
    public void clickRadioButtonNoTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickCardElements();
        RadioButtonPage radioButtonPage = homePage.clickMenuListRadioButton();
        Assert.assertEquals(radioButtonPage.isDisabled(),"true");
    }
}
