package com.demoqa.tests.forms;

import com.demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FormsTest extends BaseTest {
    @Test
    public void formsTest() {
        getDriver().get("https://demoqa.com/automation-practice-form");
        String title = getDriver().getTitle();
        System.out.println(title);
        Assert.assertTrue(true);
    }
}
