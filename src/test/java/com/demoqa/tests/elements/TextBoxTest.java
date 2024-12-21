package com.demoqa.tests.elements;

import com.demoqa.base.Base;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.TextBoxPage;
import com.demoqa.tests.elements.data_provider.TextBoxDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TextBoxTest extends Base {
    @Test(dataProvider = "textBoxData", dataProviderClass = TextBoxDataProvider.class)
    public void textBoxTest(String name, String email, String currentAddress, String permanentAddress) {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickCardElements();
        TextBoxPage textBoxPage = homePage.clickMenuListTextBox();
        textBoxPage.fillForm(name, email, currentAddress, permanentAddress);
        Assert.assertEquals(textBoxPage.getOutputName(),"Name:"+ name);
        Assert.assertEquals(textBoxPage.getOutputEmail(),"Email:"+ email);
        Assert.assertEquals(textBoxPage.getOutputCurrentAddress(), "Current Address :" + currentAddress);
        Assert.assertEquals(textBoxPage.getOutputPermanentAddress(), "Permananet Address :" + permanentAddress);


    }
}
