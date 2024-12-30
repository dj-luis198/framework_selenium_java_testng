package com.demoqa.tests.elements;

import com.demoqa.base.Base;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.TextBoxPage;
import com.demoqa.utils.data_provider.TextBoxDataProvider;
import com.demoqa.utils.java_utility.JavaUtility;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TextBoxTest extends Base {
    @Test(dataProvider = "textBoxData", dataProviderClass = TextBoxDataProvider.class)
    public void textBoxTest(String name, String email, String currentAddress, String permanentAddress, String output) {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickCardElements();
        TextBoxPage textBoxPage = homePage.clickMenuListTextBox();
        textBoxPage.fillForm(name, email, currentAddress, permanentAddress);
        Assert.assertEquals(JavaUtility.replaceBreaks(textBoxPage.getOutput()), output);
    }
}
