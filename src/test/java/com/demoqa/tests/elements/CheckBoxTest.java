package com.demoqa.tests.elements;

import com.demoqa.base.Base;
import com.demoqa.pages.CheckBoxPage;
import com.demoqa.pages.HomePage;
import com.demoqa.utils.data_provider.CheckBoxDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckBoxTest extends Base {

    @Test
    public void checkBoxExpandAndCollapseTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickCardElements();
        CheckBoxPage checkBoxPage = homePage.clickMenuListCheckBox();
        checkBoxPage.clickExpandAllButton();
        Assert.assertTrue(checkBoxPage.isExpanded());
        checkBoxPage.clickCollapseAllButton();
        Assert.assertTrue(checkBoxPage.isCollapsed());
    }

    @Test
    public void checkBoxExpandButtonTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickCardElements();
        CheckBoxPage checkBoxPage = homePage.clickMenuListCheckBox();
        checkBoxPage.clickExpandButtons();
    }

    @Test(dataProvider="checkBoxData", dataProviderClass = CheckBoxDataProvider.class)
    public void checkBoxTest(String output, String checkBoxes) {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickCardElements();
        CheckBoxPage checkBoxPage = homePage.clickMenuListCheckBox();
        checkBoxPage.clickExpandAllButton();
        checkBoxPage.selectCheckBoxes(checkBoxes);
        Assert.assertEquals(checkBoxPage.getOutputCheckBoxes(), "You have selected : "+output);
        Assert.assertTrue(checkBoxPage.verifyCkeckedCheckboxes(output));
    }
}
