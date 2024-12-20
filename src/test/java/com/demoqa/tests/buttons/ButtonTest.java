package com.demoqa.tests.buttons;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.butons.ButtonsPage;
import com.demoqa.pages.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.*;

public class ButtonTest extends BaseTest {

    @Test
    public void buttonsTest() throws InterruptedException {
        getDriver().get("https://demoqa.com");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickCardElements();
        ButtonsPage buttonPage = homePage.clickMenuListButtons();
        buttonPage.clickDoubleClickButton();
        String title=buttonPage.getTitlePage();
        Assert.assertEquals(title,"DEMOQA");
    }

//    @Test
//    public void buttonsTest2() {
//        getDriver().get("https://demoqa.com/checkbox");
//        String url = getDriver().getCurrentUrl();
//        System.out.println(url);
//        Assert.assertTrue(true);
//    }
}
